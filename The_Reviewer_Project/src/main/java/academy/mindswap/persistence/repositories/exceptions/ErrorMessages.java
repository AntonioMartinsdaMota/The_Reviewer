package academy.mindswap.persistence.repositories.exceptions;

public final class ErrorMessages {

    private ErrorMessages(){
    }

    public static final String USER_NOT_FOUND = "Can't find any user with the id %f.";
    public static final String INVALID_USER = "Invalid user";
    public static final String USER_ALREADY_EXISTS = "The user %s already exists. Try another one, please.";
    public static final String MOVIE_NOT_FOUND = "Can't find any movie with the title " + "%s";
    public static final String DIRECTOR_NOT_FOUND = "Can´t find any director with that name.";
    public static final String RATING_OUT_OF_RANGE = "The rating selected is out of range.";
    public static final String YEAR_OUT_OF_RANGE = "The year selected is out of range.";
    public static final String REVIEW_NOT_FOUND = "Can´t find any review with the id %s.";



}
