package pl.polsl.temperature.gateway;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.polsl.temperature.base.BaseModel;
import pl.polsl.temperature.exception.WrongBodyException;
import pl.polsl.temperature.station.Station;
import pl.polsl.temperature.user.User;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "gateway")
@ToString
@Data
public class Gateway extends BaseModel {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = ALL, mappedBy = "gateway")
    private List<Station> stations;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Override
    public void checkPostModel() throws WrongBodyException {
        if (name == null || user == null || user.getId() == null)
            throw new WrongBodyException("gateway::id && user:id");
        id = null;
    }
}
