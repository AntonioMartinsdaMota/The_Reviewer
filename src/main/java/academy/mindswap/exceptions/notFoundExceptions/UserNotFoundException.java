package academy.mindswap.exceptions.notFoundExceptions;

import academy.mindswap.exceptions.ErrorMessages;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id){
        super(String.format(ErrorMessages.USER_NOT_FOUND, id));
    }

}
