package pl.polsl.temperature.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.polsl.temperature.base.BaseModel;
import pl.polsl.temperature.gateway.GatewayView;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserView {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String username;
    private Set<Long> rolesId;
    private List<GatewayView> gateways;

    public UserView(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.rolesId = user.getRoles().stream().map(BaseModel::getId).collect(Collectors.toSet());
        this.gateways = user.getGateways().stream().map(GatewayView::new).collect(Collectors.toList());
    }

}
