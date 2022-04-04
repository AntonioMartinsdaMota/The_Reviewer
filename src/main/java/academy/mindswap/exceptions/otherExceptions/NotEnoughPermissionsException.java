package academy.mindswap.exceptions.otherExceptions;

import academy.mindswap.exceptions.ErrorMessages;

public class NotEnoughPermissionsException extends RuntimeException{
    public NotEnoughPermissionsException(){
        super(ErrorMessages.NOT_ENOUGH_PERMISSIONS);
    }
}
