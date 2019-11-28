package pl.polsl.temperature.gateway;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import pl.polsl.temperature.station.StationReducedView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class GatewayView {

    @ApiModelProperty(required = true, example = "0")
    private Long id;
    @ApiModelProperty(required = true, example = "Main gateway")
    private String name;
    private List<StationReducedView> stations;

    public GatewayView(Gateway gateway){
        this.id = gateway.getId();
        this.name = gateway.getName();
        if(gateway.getStations() != null)
            this.stations = gateway.getStations().stream().map(StationReducedView::new).collect(Collectors.toList());
        else
            this.stations = new ArrayList<>();
    }

}
