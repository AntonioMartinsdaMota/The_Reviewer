package academy.mindswap.exceptions;

import academy.mindswap.exceptions.badRequestExceptions.InvalidUserIdException;
import academy.mindswap.exceptions.badRequestExceptions.RatingOutOfRangeException;
import academy.mindswap.exceptions.badRequestExceptions.YearOutOfRangeException;
import academy.mindswap.exceptions.notFoundExceptions.*;
import academy.mindswap.exceptions.otherExceptions.LoginRequestFailedException;
import academy.mindswap.exceptions.otherExceptions.NotEnoughPermissionsException;
import academy.mindswap.exceptions.otherExceptions.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;

import static org.springframework.http.HttpStatus.*;


@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class, CookieNotFoundException.class, DirectorNotFoundException.class,
            MovieNotFoundException.class, ReviewNotFoundException.class})
    public ResponseEntity<Error> dealWithNotFound(Exception e, HttpServletRequest request){
        return ResponseEntity.status(NOT_FOUND)
                .body(buildError(e, request, e.getMessage()));
    }


    @ExceptionHandler(value = {InvalidUserIdException.class, RatingOutOfRangeException.class, YearOutOfRangeException.class})
    public ResponseEntity<Error>dealWithBadRequest(Exception e, HttpServletRequest request){
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(buildError(e, request, e.getMessage()));
    }

    @ExceptionHandler(value = {LoginRequestFailedException.class})
    public ResponseEntity<Error>dealFailedLogin(Exception e, HttpServletRequest request){
        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(buildError(e, request, e.getMessage()));
    }

    @ExceptionHandler(value = {NotEnoughPermissionsException.class})
    public ResponseEntity<Error>dealNotEnoughPermissions(Exception e, HttpServletRequest request){
        return ResponseEntity
                .status(FORBIDDEN)
                .body(buildError(e, request, e.getMessage()));
    }

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<Error>dealWithUserAlreadyExists(Exception e, HttpServletRequest request){
        return ResponseEntity
                .status(CONFLICT)
                .body(buildError(e, request, e.getMessage()));
    }


    private Error buildError(Exception e, HttpServletRequest request, String statusCode){
        return Error.builder()
                .timestamp(new Date())
                .verb(request.getMethod())
                .path(request.getRequestURI())
                .statusCode(statusCode)
                .message(e.getMessage())
                .build();
    }
}
