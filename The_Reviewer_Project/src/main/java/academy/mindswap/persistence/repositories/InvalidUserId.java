package academy.mindswap.persistence.repositories;

import java.util.function.IntConsumer;

public class InvalidUserId extends RuntimeException{

    IntConsumer consumer = (int i) -> {
        throw new InvalidUserId("");
    };

    public InvalidUserId(String id) {
        super(String.format("This user %s can't be found around here.", id));
    }
}
