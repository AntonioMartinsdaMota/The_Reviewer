package academy.mindswap.exceptions.otherExceptions;

import academy.mindswap.exceptions.ErrorMessages;

public class ReviewAlreadyExistsException extends RuntimeException{
    public ReviewAlreadyExistsException(){
        super(ErrorMessages.REVIEW_ALREADY_EXISTS);
    }
}
