package pl.polsl.temperature.gateway;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.polsl.temperature.station.StationReducedView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class GatewayView {

    private Long id;
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
