package pl.polsl.temperature.token;

import lombok.NonNull;
import pl.polsl.temperature.exception.NotAuthorizedActionException;
import pl.polsl.temperature.exception.NotFoundException;
import pl.polsl.temperature.user.User;

public interface TokenRepository {

    @NonNull User getUserFromHeader(String tokenHeader) throws NotAuthorizedActionException;

    @NonNull User getAndValidateUserFromHeader(String tokenHeader, Long userId) throws NotAuthorizedActionException, NotFoundException;

    void validateUserWithHeader(String tokenHeader, User user) throws NotAuthorizedActionException;

}
