package academy.mindswap.exceptions.badRequestExceptions;

import academy.mindswap.exceptions.ErrorMessages;

public class YearOutOfRangeException extends RuntimeException{

    public YearOutOfRangeException(){
        super(ErrorMessages.YEAR_OUT_OF_RANGE);
    }
}
