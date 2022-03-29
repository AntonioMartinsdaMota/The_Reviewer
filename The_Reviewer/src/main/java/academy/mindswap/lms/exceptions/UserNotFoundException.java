package academy.mindswap.lms.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String id){
        super(String.format("That particular user %s can't be found around here. Did he run away?", id) );
    }
}
