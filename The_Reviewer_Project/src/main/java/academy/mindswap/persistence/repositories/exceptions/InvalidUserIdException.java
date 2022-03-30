package academy.mindswap.persistence.repositories.exceptions;

import java.util.function.IntConsumer;

public class InvalidUserIdException extends RuntimeException{

    IntConsumer consumer = (int i) -> {
        throw new InvalidUserIdException("");
    };

    public InvalidUserIdException(String id) {
        super(String.format(ErrorMessages.INVALID_USER, id));
    }
}
