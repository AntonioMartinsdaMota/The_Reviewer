package academy.mindswap.persistence.repositories.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id){
        super(String.format(ErrorMessages.USER_NOT_FOUND, id));
    }

}
