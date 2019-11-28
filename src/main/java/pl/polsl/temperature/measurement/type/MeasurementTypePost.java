package pl.polsl.temperature.measurement.type;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MeasurementTypePost {

    @ApiModelProperty(required = true, example = "Temperature")
    private String name;
    @ApiModelProperty(required = true)
    private Long userId;

}
