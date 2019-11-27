package pl.polsl.temperature.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthenticationPost {

    private String username;
    private String password;

}