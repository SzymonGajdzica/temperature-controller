package pl.polsl.temperature.measurement.type;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MeasurementTypeView {

    private Long id;
    private String name;

    public MeasurementTypeView(MeasurementType measurementType){
        this.id = measurementType.getId();
        this.name = measurementType.getName();
    }

}
