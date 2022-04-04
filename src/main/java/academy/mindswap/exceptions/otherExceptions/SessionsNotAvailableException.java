package academy.mindswap.exceptions.otherExceptions;

import academy.mindswap.exceptions.ErrorMessages;

public class SessionsNotAvailableException extends RuntimeException{
    public SessionsNotAvailableException(){
        super(ErrorMessages.SESSIONS_NOT_AVAILABLE);
    }
}
