package academy.mindswap.exceptions.notFoundExceptions;

import academy.mindswap.exceptions.ErrorMessages;

public class CookieNotFoundException extends RuntimeException{
    public CookieNotFoundException(){
        super(ErrorMessages.COOKIE_NOT_FOUND);
    }
}
