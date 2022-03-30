package academy.mindswap.exceptions.badRequestExceptions;

import academy.mindswap.exceptions.ErrorMessages;

import java.util.function.IntConsumer;

public class InvalidUserIdException extends RuntimeException{

    IntConsumer consumer = (int i) -> {
        throw new InvalidUserIdException("");
    };

    public InvalidUserIdException(String id) {
        super(String.format(ErrorMessages.INVALID_USER, id));
    }
}
