package academy.mindswap.exceptions.notFoundExceptions;

import academy.mindswap.exceptions.ErrorMessages;

public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException(){
        super(ErrorMessages.MOVIE_NOT_FOUND);
    }
}
