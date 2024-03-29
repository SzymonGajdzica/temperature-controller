package pl.polsl.temperature.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.polsl.temperature.base.BaseModel;
import pl.polsl.temperature.gateway.Gateway;
import pl.polsl.temperature.measurement.type.MeasurementType;
import pl.polsl.temperature.role.Role;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "\"user\"")
@ToString
@Data
public class User extends BaseModel {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany
    private Set<Role> roles;

    @OneToMany(cascade = ALL, mappedBy = "user")
    private List<Gateway> gateways;

    @OneToMany(cascade = ALL, mappedBy = "ownerUser")
    private List<MeasurementType> measurementTypes;

}
