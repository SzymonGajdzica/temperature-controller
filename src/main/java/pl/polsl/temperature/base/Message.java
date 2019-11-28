package pl.polsl.temperature.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Message {

    @ApiModelProperty(required = true, example = "Authentication problem")
    @Setter(AccessLevel.NONE)
    private String title;

    @ApiModelProperty(required = true, example = "User could not be authenticated")
    @Setter(AccessLevel.NONE)
    private String description;

}
