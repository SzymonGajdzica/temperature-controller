package pl.polsl.temperature.station;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class StationPost {

    @ApiModelProperty(required = true, example = "Josh temperature station")
    private String name;
    @ApiModelProperty(required = true)
    private Long gatewayId;

}
