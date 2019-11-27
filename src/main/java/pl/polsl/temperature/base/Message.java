package pl.polsl.temperature.base;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Message {

    @Setter(AccessLevel.NONE)
    private String title;

    @Setter(AccessLevel.NONE)
    private String description;

}
