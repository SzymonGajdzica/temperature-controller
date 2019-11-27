package pl.polsl.temperature.exception;

public class UsernameAlreadyUsedException extends RuntimeException {

    public UsernameAlreadyUsedException(String userName){
        super("User with name '" + userName + "' already exists");
    }

}
