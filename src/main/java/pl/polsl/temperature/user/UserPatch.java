package pl.polsl.temperature.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserPatch {

    @ApiModelProperty(example = "John")
    private String name;
    @ApiModelProperty(example = "Bosh")
    private String surname;
    @ApiModelProperty(example = "John33@gmail.com")
    private String email;

}
