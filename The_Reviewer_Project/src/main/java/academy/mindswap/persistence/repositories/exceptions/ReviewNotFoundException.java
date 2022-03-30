package academy.mindswap.persistence.repositories.exceptions;

public class ReviewNotFoundException extends RuntimeException{

    public ReviewNotFoundException(){
        super(ErrorMessages.REVIEW_NOT_FOUND);
    }
}
