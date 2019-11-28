package pl.polsl.temperature.station;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.polsl.temperature.measurement.MeasurementView;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
    @ApiModelProperty(required = true, example = "eb1b9ba7-a44d-4826-84e0-03eaf5f570cc")
    private UUID secretId;

    public StationView(Station station){
        this.id = station.getId();
        this.name = station.getName();
        this.secretId = station.getSecretId();
        if(station.getMeasurements() != null)
            this.measurements = station.getMeasurements().stream().map(MeasurementView::new).collect(Collectors.toList());
        else
            this.measurements = new ArrayList<>();
    }

}
