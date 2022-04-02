package academy.mindswap.exceptions.badRequestExceptions;

import academy.mindswap.exceptions.ErrorMessages;

public class InvalidAssertAuthoritiesException extends RuntimeException{
    public InvalidAssertAuthoritiesException() {
        super(ErrorMessages.INVALID_ASSERT_AUTHORITIES);
    }
}
