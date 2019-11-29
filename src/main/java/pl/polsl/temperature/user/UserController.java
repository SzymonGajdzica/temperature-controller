package pl.polsl.temperature.user;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.polsl.temperature.base.Message;
import pl.polsl.temperature.exception.NotAuthorizedActionException;
import pl.polsl.temperature.token.TokenRepository;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserView> getUsers(@ApiIgnore @RequestHeader(value = "Authorization") String tokenHeader) {
        User user = tokenRepository.getUserFromHeader(tokenHeader);
        if(user.getRoles().stream().noneMatch(role -> role.getName().equals("admin")))
            throw new NotAuthorizedActionException("this action requires admin privileges");
        return userRepository.findAll().stream().map(UserView::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserView getUser(@ApiIgnore @RequestHeader(value = "Authorization") String tokenHeader) {
        return new UserView(tokenRepository.getUserFromHeader(tokenHeader));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Message deleteUser(@PathVariable Long id, @ApiIgnore @RequestHeader(value = "Authorization") String tokenHeader) {
        userRepository.delete(tokenRepository.getAndValidateUserFromHeader(tokenHeader, id));
        return new Message("Success", "User deleted");
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message updateUser(@PathVariable Long id, @RequestBody UserPatch userPatch, @ApiIgnore @RequestHeader(value = "Authorization") String tokenHeader) {
        User user = tokenRepository.getAndValidateUserFromHeader(tokenHeader, id);
        if(userPatch.getName() != null)
            user.setName(userPatch.getName());
        if(userPatch.getSurname() != null)
            user.setSurname(userPatch.getSurname());
        if(userPatch.getEmail() != null)
            user.setEmail(userPatch.getEmail());
        userRepository.save(user);
        return new Message("Success", "User updated");
    }

}
