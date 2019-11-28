package pl.polsl.temperature.station;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.polsl.temperature.measurement.MeasurementView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class StationView {

    @ApiModelProperty(required = true, example = "0")
    private Long id;
    @ApiModelProperty(required = true, example = "Josh temperature station")
    private String name;
    @ApiModelProperty(required = true)
    private List<MeasurementView> measurements;

    public StationView(Station station){
        this.id = station.getId();
        this.name = station.getName();
        if(station.getMeasurements() != null)
            this.measurements = station.getMeasurements().stream().map(MeasurementView::new).collect(Collectors.toList());
        else
            this.measurements = new ArrayList<>();
    }

}
