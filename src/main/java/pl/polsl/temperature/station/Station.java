package pl.polsl.temperature.station;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.polsl.temperature.base.BaseModel;
import pl.polsl.temperature.gateway.Gateway;
import pl.polsl.temperature.measurement.Measurement;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "station")
@ToString
@Data
public class Station extends BaseModel {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = ALL, mappedBy = "station")
    private List<Measurement> measurements;

    @ManyToOne()
    @JoinColumn(name = "gateway_id", nullable = false)
    private Gateway gateway;

    @Column(name = "secret_id", nullable = false)
    private UUID secretId = UUID.randomUUID();


}
