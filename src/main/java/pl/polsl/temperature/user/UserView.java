package pl.polsl.temperature.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.polsl.temperature.base.BaseModel;
import pl.polsl.temperature.gateway.GatewayView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserView {

    @ApiModelProperty(required = true, example = "0")
    private Long id;
    @ApiModelProperty(required = true, example = "John")
    private String name;
    @ApiModelProperty(required = true, example = "Bosh")
    private String surname;
    @ApiModelProperty(required = true, example = "John33@gmail.com")
    private String email;
    @ApiModelProperty(required = true, example = "John33")
    private String username;
    @ApiModelProperty(required = true)
    private Set<Long> rolesId;
    @ApiModelProperty(required = true)
    private List<GatewayView> gateways;

    public UserView(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.username = user.getUsername();
        if(user.getRoles() != null)
            this.rolesId = user.getRoles().stream().map(BaseModel::getId).collect(Collectors.toSet());
        else
            this.rolesId = new HashSet<>();
        if(user.getGateways() != null)
            this.gateways = user.getGateways().stream().map(GatewayView::new).collect(Collectors.toList());
        else
            this.gateways = new ArrayList<>();

    }

}
