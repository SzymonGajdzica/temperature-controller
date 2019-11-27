package pl.polsl.temperature.station;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.temperature.measurement.MeasurementView;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class StationReducedView extends StationView {

    @JsonIgnore
    @Override
    public List<MeasurementView> getMeasurements() {
        return super.getMeasurements();
    }

    public StationReducedView(Station station){
        super(station);
    }

}
