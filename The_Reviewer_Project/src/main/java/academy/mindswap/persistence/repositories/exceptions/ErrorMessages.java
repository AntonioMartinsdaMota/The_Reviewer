package academy.mindswap.persistence.repositories.exceptions;

public final class ErrorMessages {

    private ErrorMessages(){
    }

    public static final String USER_NOT_FOUND = "Can't find any user with the id %s.";
    public static final String INVALID_USER = "Invalid user";
    public static final String USER_ALREADY_EXISTS = "The user %s already exists. Try another one, please.";
    public static final String MOVIE_NOT_FOUND = "Can't find any movie with the title " + "%s";


}
