package pl.polsl.temperature.token;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import pl.polsl.temperature.exception.NotAuthorizedActionException;
import pl.polsl.temperature.exception.NotFoundException;
import pl.polsl.temperature.jwt.AuthenticationUtils;
import pl.polsl.temperature.user.User;
import pl.polsl.temperature.user.UserRepository;

import java.util.Optional;

@AllArgsConstructor
@Component
public class TokenRepositoryImpl implements TokenRepository {

    private final UserRepository userRepository;
    private final AuthenticationUtils authenticationUtils;

    @Override
    public @NonNull User getUserFromHeader(String tokenHeader) throws NotAuthorizedActionException {
        String userName = getUsernameFromHeader(tokenHeader);
        Optional<User> user = userRepository.findByUsername(userName);
        if(!user.isPresent())
            throw new NotAuthorizedActionException("token does not match any user");
        return user.get();
    }

    @Override
    public @NonNull User getAndValidateUserFromHeader(String tokenHeader, Long userId) throws NotAuthorizedActionException, NotFoundException {
        String userName = getUsernameFromHeader(tokenHeader);
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent())
            throw new NotFoundException(userId);
        if(!userName.equals(user.get().getUsername()))
            throw new NotAuthorizedActionException("cannot perform actions on not your own data");
        return user.get();
    }

    @Override
    public void validateUserWithHeader(String tokenHeader, User user) throws NotAuthorizedActionException {
        String userName = getUsernameFromHeader(tokenHeader);
        if (!userName.equals(user.getUsername()))
            throw new NotAuthorizedActionException("cannot perform actions on not your own data");
    }

    private String getUsernameFromHeader(String tokenHeader) throws NotAuthorizedActionException {
        try {
            String token = authenticationUtils.getTokenFromHeader(tokenHeader);
            return authenticationUtils.getClaimFromToken(token, Claims::getSubject);
        }catch (Exception e){
            throw new NotAuthorizedActionException("unknown error");
        }
    }

}
