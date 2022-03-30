package academy.mindswap.exceptions.otherExceptions;

import academy.mindswap.exceptions.ErrorMessages;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(){
        super(ErrorMessages.USER_ALREADY_EXISTS);
    }

}
