package academy.mindswap.exceptions.badRequestExceptions;

import academy.mindswap.exceptions.ErrorMessages;

public class RatingOutOfRangeException extends RuntimeException{

    public RatingOutOfRangeException(){
        super(ErrorMessages.RATING_OUT_OF_RANGE);
    }
}
