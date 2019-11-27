package pl.polsl.temperature.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.polsl.temperature.base.Message;

@ControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Message notFoundHandler(NotFoundException e) {
        return generateBasicMessage(e);
    }

    @ResponseBody
    @ExceptionHandler(NotAuthorizedActionException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Message notAuthorizedActionHandler(NotAuthorizedActionException e) {
        return generateBasicMessage(e);
    }

    @ResponseBody
    @ExceptionHandler(UsernameAlreadyUsedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Message usernameAlreadyUsedHandler(UsernameAlreadyUsedException e) {
        return generateBasicMessage(e);
    }

    private Message generateBasicMessage(Exception e){
        return new Message(e.getClass().getSimpleName(), e.getMessage());
    }


}
