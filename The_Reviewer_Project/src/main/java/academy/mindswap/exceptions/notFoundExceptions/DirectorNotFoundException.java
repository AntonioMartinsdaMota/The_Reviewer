package academy.mindswap.exceptions.notFoundExceptions;

import academy.mindswap.exceptions.ErrorMessages;

public class DirectorNotFoundException extends RuntimeException {

    public DirectorNotFoundException(){
        super(ErrorMessages.DIRECTOR_NOT_FOUND);
    }
}
