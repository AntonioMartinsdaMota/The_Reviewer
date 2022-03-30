package academy.mindswap.persistence.repositories.exceptions;

public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException(){
        super(ErrorMessages.MOVIE_NOT_FOUND);
    }
}
