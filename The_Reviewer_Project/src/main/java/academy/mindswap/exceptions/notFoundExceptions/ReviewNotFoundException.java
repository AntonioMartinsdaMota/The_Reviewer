package academy.mindswap.exceptions.notFoundExceptions;

import academy.mindswap.exceptions.ErrorMessages;

public class ReviewNotFoundException extends RuntimeException{

    public ReviewNotFoundException(){
        super(ErrorMessages.REVIEW_NOT_FOUND);
    }
}
