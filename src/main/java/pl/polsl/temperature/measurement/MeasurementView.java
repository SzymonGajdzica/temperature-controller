package pl.polsl.temperature.measurement;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class MeasurementView {

    private Long id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;
    private Double value;
    private Long measurementTypeId;

    public MeasurementView(Measurement measurement){
        this.id = measurement.getId();
        this.date = measurement.getDate();
        this.value = measurement.getValue();
        this.measurementTypeId = measurement.getMeasurementType().getId();
    }

}
