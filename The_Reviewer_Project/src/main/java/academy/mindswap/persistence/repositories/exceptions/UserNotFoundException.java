package academy.mindswap.persistence.repositories.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException (String userId){
        super(ErrorMessages.USER_NOT_FOUND);
    }

}
