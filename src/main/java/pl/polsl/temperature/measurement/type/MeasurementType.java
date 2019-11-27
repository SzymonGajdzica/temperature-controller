package pl.polsl.temperature.measurement.type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.polsl.temperature.base.BaseModel;
import pl.polsl.temperature.measurement.Measurement;
import pl.polsl.temperature.user.User;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "measurement_type")
@ToString
@Data
public class MeasurementType extends BaseModel {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = ALL, mappedBy = "measurementType")
    private List<Measurement> measurements;

    @ManyToOne()
    @JoinColumn(name = "owner_user_id", nullable = false)
    private User ownerUser;
}
