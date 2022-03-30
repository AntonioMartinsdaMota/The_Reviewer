package academy.mindswap.exceptions.otherExceptions;

import academy.mindswap.exceptions.ErrorMessages;

public class LoginRequestFailedException extends RuntimeException{
    public LoginRequestFailedException(){
        super(ErrorMessages.LOGIN_FAILED);
    }
}
