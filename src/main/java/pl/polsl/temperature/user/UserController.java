package pl.polsl.temperature.user;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.polsl.temperature.exception.NotAuthorizedActionException;
import pl.polsl.temperature.token.TokenRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserView> getAllUsers(@RequestHeader("Authorization") String tokenHeader) {
        User user = tokenRepository.getUserFromHeader(tokenHeader);
        if(user.getRoles().stream().noneMatch(role -> role.getName().equals("admin")))
            throw new NotAuthorizedActionException("this action requires admin privileges");
        return userRepository.findAll().stream().map(UserView::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/current", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserView getSingleUser(@RequestHeader("Authorization") String tokenHeader) {
        return new UserView(tokenRepository.getUserFromHeader(tokenHeader));
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable Long id, @RequestHeader("Authorization") String tokenHeader) {
        userRepository.delete(tokenRepository.getAndValidateUserFromHeader(tokenHeader, id));
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@PathVariable Long id, @RequestBody UserPatch userPatch, @RequestHeader("Authorization") String tokenHeader) {
        User user = tokenRepository.getAndValidateUserFromHeader(tokenHeader, id);
        if(userPatch.getName() != null)
            user.setName(userPatch.getName());
        if(userPatch.getSurname() != null)
            user.setSurname(userPatch.getSurname());
        if(userPatch.getEmail() != null)
            user.setEmail(userPatch.getEmail());
        userRepository.save(user);
    }

}
