package academy.mindswap.persistence.repositories.exceptions;

import academy.mindswap.persistence.models.User;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(User id){
        super(ErrorMessages.USER_NOT_FOUND);
    }

}
