package pl.polsl.temperature.measurement;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class MeasurementView {

    @ApiModelProperty(required = true, example = "0")
    private Long id;
    @ApiModelProperty(required = true, example = "2019-11-27T10:57:43.019+01:00")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;
    @ApiModelProperty(required = true, example = "10.0")
    private Double value;
    @ApiModelProperty(required = true, example = "0")
    private Long measurementTypeId;

    public MeasurementView(Measurement measurement){
        this.id = measurement.getId();
        this.date = measurement.getDate();
        this.value = measurement.getValue();
        this.measurementTypeId = measurement.getMeasurementType().getId();
    }

}
