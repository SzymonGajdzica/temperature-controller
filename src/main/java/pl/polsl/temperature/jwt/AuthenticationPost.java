package pl.polsl.temperature.jwt;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthenticationPost {

    @ApiModelProperty(required = true, example = "John33")
    private String username;
    @ApiModelProperty(required = true, example = "JohnBosh123")
    private String password;

}