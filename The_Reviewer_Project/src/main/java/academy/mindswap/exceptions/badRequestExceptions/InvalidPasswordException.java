package academy.mindswap.exceptions.badRequestExceptions;

import academy.mindswap.exceptions.ErrorMessages;

public class InvalidPasswordException extends RuntimeException{

    public InvalidPasswordException() {
        super(String.format(ErrorMessages.INVALID_PASSWORD));
    }
}
