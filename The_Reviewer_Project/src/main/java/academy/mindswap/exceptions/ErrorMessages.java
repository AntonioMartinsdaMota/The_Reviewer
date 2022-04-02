package academy.mindswap.exceptions;

public final class ErrorMessages {

    private ErrorMessages(){
    }

    public static final String USER_NOT_FOUND = "User not found";
    public static final String INVALID_USER = "Invalid user input";
    public static final String USER_ALREADY_EXISTS = "Username or email already exists. Try another one, please.";
    public static final String MOVIE_NOT_FOUND = "Movie not found";
    public static final String DIRECTOR_NOT_FOUND = "Can´t find any director with that name.";
    public static final String RATING_OUT_OF_RANGE = "The rating selected is out of range.";
    public static final String YEAR_OUT_OF_RANGE = "The year selected is out of range.";
    public static final String REVIEW_NOT_FOUND = "Review not found";
    public static final String LOGIN_FAILED = "Login failed.";
    public static final String NOT_ENOUGH_PERMISSIONS = "Not enough permissions";
    public static final String INVALID_PASSWORD = "Invalid Password";
    public static final String REVIEW_ALREADY_EXISTS = "You already made a review for this movie";
    public static final String ALREADY_LOGGED_IN = "Already logged in.";
    public static final String INVALID_PASSWORD_CHANGE_REQUEST = "Password change failed";
    public static final String INVALID_ASSERT_AUTHORITIES = "User has already OWNER or ADMIN authority";
    public static final String SESSIONS_NOT_AVAILABLE = "Sessions not available for this movie";

}
