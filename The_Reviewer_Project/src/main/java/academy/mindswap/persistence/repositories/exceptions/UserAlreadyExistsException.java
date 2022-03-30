package academy.mindswap.persistence.repositories.exceptions;

import academy.mindswap.commands.UserDto;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String Userid){
        super(ErroMessages.USER_ALREADY_EXISTS);
    }

}
