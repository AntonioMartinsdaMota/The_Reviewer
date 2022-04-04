package academy.mindswap.exceptions.badRequestExceptions;

import academy.mindswap.exceptions.ErrorMessages;
public class InvalidPasswordChangeRequestException extends RuntimeException {
    public InvalidPasswordChangeRequestException() {
        super(ErrorMessages.INVALID_PASSWORD_CHANGE_REQUEST);
    }
}
