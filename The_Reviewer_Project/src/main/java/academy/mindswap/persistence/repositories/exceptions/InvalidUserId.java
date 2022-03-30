package academy.mindswap.persistence.repositories.exceptions;

import java.util.function.IntConsumer;

public class InvalidUserId extends RuntimeException{

    IntConsumer consumer = (int i) -> {
        throw new InvalidUserId("");
    };

    public InvalidUserId(String id) {
        super(String.format(ErrorMessages.INVALID_USER, id));
    }
}
