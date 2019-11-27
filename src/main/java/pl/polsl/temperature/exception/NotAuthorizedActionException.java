package pl.polsl.temperature.exception;

public class NotAuthorizedActionException extends RuntimeException {

    public NotAuthorizedActionException(String message){
        super("Not authorized - " + message);
    }

}
