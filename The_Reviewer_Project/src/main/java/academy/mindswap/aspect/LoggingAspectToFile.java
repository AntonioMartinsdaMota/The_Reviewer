package academy.mindswap.aspect;

import academy.mindswap.utils.LogWriter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;

import java.util.Date;
import java.util.Optional;

public class LoggingAspectToFile {

    @AfterReturning(value = "execution(* academy.mindswap.controllers.UserController.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Optional result) {
        if (result.isPresent()) {
            LogWriter.writeToFile("After returning from " + joinPoint.getSignature().getName() + " with result " + result.get());
        }
    }

    @AfterReturning(value = "execution(* academy.mindswap.controllers.MovieController.*(..))", returning = "result")
    public void logAfterReturning1(JoinPoint joinPoint, Optional result) {
        if (result.isPresent()) {
            LogWriter.writeToFile("After returning from " + joinPoint.getSignature().getName() + " with result " + result.get());
        }
    }

    @AfterReturning(value = "execution(* academy.mindswap.controllers.ReviewController.*(..))", returning = "result")
    public void logAfterReturning3(JoinPoint joinPoint, Optional result) {
        if (result.isPresent()) {
            LogWriter.writeToFile("After returning from " + joinPoint.getSignature().getName() + " with result " + result.get());
        }
    }


    @AfterThrowing(value = "execution(* academy.mindswap.services.*Service.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        LogWriter.writeToFile("After throwing from " + joinPoint.getSignature().getName() + " with exception " + ex.getMessage());
    }
}
