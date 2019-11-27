package pl.polsl.temperature.user;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserPatch {

    private String name;
    private String surname;
    private String email;

}
