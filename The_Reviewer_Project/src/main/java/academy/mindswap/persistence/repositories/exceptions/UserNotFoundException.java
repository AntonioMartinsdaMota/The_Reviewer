package academy.mindswap.persistence.repositories.exceptions;

import academy.mindswap.commands.UserDto;
import academy.mindswap.persistence.models.User;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id){
        super(String.format(ErrorMessages.USER_NOT_FOUND, id));
    }

}
