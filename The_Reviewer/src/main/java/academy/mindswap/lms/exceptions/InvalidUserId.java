package academy.mindswap.lms.exceptions;

import java.util.function.IntConsumer;

public class InvalidUserId extends RuntimeException {

  IntConsumer consumer = (int i) -> {
    throw new InvalidUserId("");
  };

    public InvalidUserId(String id) {
        super(String.format("MF! %s can't be found around here. Are U dumb FMF?", id) );
      //  IntStream.rangeClosed(0, 9).forEach(consumer);
    }
}
