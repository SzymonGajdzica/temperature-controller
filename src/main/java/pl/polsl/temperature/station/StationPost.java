package pl.polsl.temperature.station;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class StationPost {

    private String name;
    private Long gatewayId;

}
