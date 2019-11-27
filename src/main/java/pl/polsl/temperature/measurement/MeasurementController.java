package pl.polsl.temperature.measurement;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/measurements")
@AllArgsConstructor
public class MeasurementController {

    private final MeasurementRepository repository;



}
