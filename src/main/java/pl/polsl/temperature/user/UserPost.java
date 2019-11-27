package pl.polsl.temperature.user;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserPost {

    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;

}
