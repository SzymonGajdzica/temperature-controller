package pl.polsl.temperature.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserPost {

    @ApiModelProperty(required = true, example = "John")
    private String name;
    @ApiModelProperty(required = true, example = "Bosh")
    private String surname;
    @ApiModelProperty(required = true, example = "John33@gmail.com")
    private String email;
    @ApiModelProperty(required = true, example = "John33")
    private String username;
    @ApiModelProperty(required = true, example = "JohnBosh123")
    private String password;

}
