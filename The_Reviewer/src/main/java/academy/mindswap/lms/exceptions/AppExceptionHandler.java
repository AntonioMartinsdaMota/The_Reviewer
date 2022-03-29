package academy.mindswap.lms.exceptions;

import academy.mindswap.lms.exceptions.error.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Error> dealWithNotFound(Exception e, HttpServletRequest request) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(buildError(e, request, NOT_FOUND.toString()));
    }

    @ExceptionHandler(value = {InvalidUserId.class})
    public ResponseEntity<Error> dealWithNegativeId(Exception e, HttpServletRequest request) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .header("X-Error", "Invalid User Id")
                .body(buildError(e, request, BAD_REQUEST.toString()));
    }


    private Error buildError(Exception e, HttpServletRequest request, String statusCode) {
        return Error.builder()
                .timestamp(new Date())
                .verb(request.getMethod())
                .path(request.getRequestURI())
                .statusCode(statusCode)
                .message(e.getMessage())
                .build();
    }
}
