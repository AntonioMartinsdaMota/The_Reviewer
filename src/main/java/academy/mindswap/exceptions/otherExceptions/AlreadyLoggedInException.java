package academy.mindswap.exceptions.otherExceptions;

import academy.mindswap.exceptions.ErrorMessages;

public class AlreadyLoggedInException extends RuntimeException{
        public AlreadyLoggedInException(){
            super(ErrorMessages.ALREADY_LOGGED_IN);
        }
}
