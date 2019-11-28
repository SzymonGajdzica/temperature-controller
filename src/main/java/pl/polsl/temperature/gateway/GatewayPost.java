package pl.polsl.temperature.gateway;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class GatewayPost {

    @ApiModelProperty(required = true, example = "Main gateway")
    private String name;
    @ApiModelProperty(required = true)
    private Long userId;

}
