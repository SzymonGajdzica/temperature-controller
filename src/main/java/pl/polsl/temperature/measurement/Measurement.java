package pl.polsl.temperature.measurement;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.polsl.temperature.base.BaseModel;
import pl.polsl.temperature.measurement.type.MeasurementType;
import pl.polsl.temperature.station.Station;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "measurement")
@ToString
@Data
public class Measurement extends BaseModel {

    @Column(name = "date", nullable = false)
    @Setter(AccessLevel.NONE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date = new Date();

    @Column(name = "value", nullable = false)
    private Double value;

    @ManyToOne()
    @JoinColumn(name = "measurement_type_id", nullable = false)
    private MeasurementType measurementType;

    @ManyToOne()
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;
}
