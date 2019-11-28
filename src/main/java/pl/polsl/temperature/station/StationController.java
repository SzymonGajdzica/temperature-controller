package pl.polsl.temperature.station;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.polsl.temperature.exception.NotFoundException;
import pl.polsl.temperature.gateway.Gateway;
import pl.polsl.temperature.gateway.GatewayRepository;
import pl.polsl.temperature.token.TokenRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stations")
@AllArgsConstructor
public class StationController {

    private final StationRepository stationRepository;
    private final GatewayRepository gatewayRepository;
    private final TokenRepository tokenRepository;

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public StationView getSingleStation(@PathVariable Long id,
                                        @RequestHeader("Authorization") String tokenHeader,
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam ZonedDateTime startDate,
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam ZonedDateTime endDate) {
        Optional<Station> station = stationRepository.findById(id);
        if(!station.isPresent())
            throw new NotFoundException(id);
        tokenRepository.validateUserWithHeader(tokenHeader, station.get().getGateway().getUser());
        Station newStation = station.get();
        newStation.setMeasurements(newStation.getMeasurements()
                .stream()
                .filter(measurement -> measurement.getDate().before(Date.from(endDate.toInstant())))
                .filter(measurement -> measurement.getDate().after(Date.from(startDate.toInstant())))
                .collect(Collectors.toList()));
        return new StationView(newStation);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public StationView addStation(@RequestBody StationPost stationPost, @RequestHeader("Authorization") String tokenHeader) {
        Station station = new Station();
        Optional<Gateway> gateway = gatewayRepository.findById(stationPost.getGatewayId());
        if(!gateway.isPresent())
            throw new NotFoundException(stationPost.getGatewayId());
        tokenRepository.validateUserWithHeader(tokenHeader, gateway.get().getUser());
        station.setName(stationPost.getName());
        station.setGateway(gateway.get());
        return new StationView(stationRepository.save(station));
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteStation(@PathVariable Long id, @RequestHeader("Authorization") String tokenHeader) {
        Optional<Station> station = stationRepository.findById(id);
        if(!station.isPresent())
            throw new NotFoundException(id);
        tokenRepository.validateUserWithHeader(tokenHeader, station.get().getGateway().getUser());
        stationRepository.delete(station.get());
    }

}
