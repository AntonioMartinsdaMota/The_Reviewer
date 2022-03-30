package academy.mindswap.persistence.repositories.exceptions;

public class YearOutOfRangeException extends RuntimeException{

    public YearOutOfRangeException(){
        super(ErrorMessages.YEAR_OUT_OF_RANGE);
    }
}
