package academy.mindswap.persistence.repositories.exceptions;

public class DirectorNotFoundException extends RuntimeException {

    public DirectorNotFoundException(){
        super(ErrorMessages.DIRECTOR_NOT_FOUND);
    }
}
