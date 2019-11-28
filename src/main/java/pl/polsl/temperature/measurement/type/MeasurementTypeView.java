package pl.polsl.temperature.measurement.type;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MeasurementTypeView {

    @ApiModelProperty(required = true, example = "0")
    private Long id;
    @ApiModelProperty(required = true, example = "Temperature")
    private String name;

    public MeasurementTypeView(MeasurementType measurementType){
        this.id = measurementType.getId();
        this.name = measurementType.getName();
    }

}
