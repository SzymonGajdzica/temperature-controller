package pl.polsl.temperature.gateway;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class GatewayPost {

    private String name;
    private Long userId;

}
