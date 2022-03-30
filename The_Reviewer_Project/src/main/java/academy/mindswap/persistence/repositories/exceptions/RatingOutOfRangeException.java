package academy.mindswap.persistence.repositories.exceptions;

public class RatingOutOfRangeException extends RuntimeException{

    public RatingOutOfRangeException(){
        super(ErrorMessages.RATING_OUT_OF_RANGE);
    }
}
