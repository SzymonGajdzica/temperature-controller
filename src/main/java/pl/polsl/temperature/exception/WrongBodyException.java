package pl.polsl.temperature.exception;

public class WrongBodyException extends RuntimeException {

    public WrongBodyException(String infoAboutMissingData){
        super("Body does not contain required values, check whether u included all of them: " + infoAboutMissingData);
    }

}
