package pl.polsl.temperature.station;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.polsl.temperature.base.BaseModel;
import pl.polsl.temperature.exception.WrongBodyException;
import pl.polsl.temperature.gateway.Gateway;
import pl.polsl.temperature.measurement.Measurement;

import javax.persistence.*;
import java.util.List;

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

    @Override
    public void checkPostModel() throws WrongBodyException {
        if(name == null || gateway == null || gateway.getId() == null)
            throw new WrongBodyException("station:name && gateway:id");
        id = null;
    }

}
