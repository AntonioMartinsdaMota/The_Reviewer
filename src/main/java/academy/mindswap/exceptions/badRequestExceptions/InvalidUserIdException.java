package academy.mindswap.exceptions.badRequestExceptions;

import academy.mindswap.exceptions.ErrorMessages;

import java.util.function.IntConsumer;

public class InvalidUserIdException extends RuntimeException{

    public InvalidUserIdException(String id) {
        super(String.format(ErrorMessages.INVALID_USER, id));
    }
}
