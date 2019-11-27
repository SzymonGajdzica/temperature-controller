package pl.polsl.temperature.measurement.type;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MeasurementTypePost {

    private String name;
    private Long userId;

}
