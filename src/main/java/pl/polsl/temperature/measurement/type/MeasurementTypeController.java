package pl.polsl.temperature.measurement.type;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.polsl.temperature.base.Message;
import pl.polsl.temperature.exception.NotFoundException;
import pl.polsl.temperature.token.TokenRepository;
import pl.polsl.temperature.user.User;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/measurement_types")
@AllArgsConstructor
public class MeasurementTypeController {

    private final MeasurementTypeRepository measurementTypeRepository;
    private final TokenRepository tokenRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MeasurementTypeView> getAllMeasurementTypes(@ApiIgnore @RequestHeader(value = "Authorization") String tokenHeader) {
        User user = tokenRepository.getUserFromHeader(tokenHeader);
        return measurementTypeRepository.findAllByOwnerUser(user).stream().map(MeasurementTypeView::new).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MeasurementTypeView addMeasurementType(@RequestBody MeasurementTypePost measurementTypePost, @ApiIgnore @RequestHeader(value = "Authorization") String tokenHeader) {
        MeasurementType measurementType = new MeasurementType();
        measurementType.setName(measurementTypePost.getName());
        measurementType.setOwnerUser(tokenRepository.getAndValidateUserFromHeader(tokenHeader, measurementTypePost.getUserId()));
        return new MeasurementTypeView(measurementTypeRepository.save(measurementType));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Message deleteMeasurementType(@PathVariable Long id, @ApiIgnore @RequestHeader(value = "Authorization") String tokenHeader) {
        MeasurementType measurementType = measurementTypeRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        tokenRepository.validateUserWithHeader(tokenHeader, measurementType.getOwnerUser());
        measurementTypeRepository.delete(measurementType);
        return new Message("Success", "Measurement type deleted");
    }

}
