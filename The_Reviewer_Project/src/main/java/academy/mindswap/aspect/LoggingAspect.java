package academy.mindswap.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class LoggingAspect {

    @AfterReturning(value = "execution(* academy.mindswap.service.UserService.getUser(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Optional result) {
        if (result.isPresent()) {
            System.out.println("After returning from " + joinPoint.getSignature().getName() + " with result " + result.get());
        }
    }
    @AfterReturning(value = "execution(* academy.mindswap.service.MovieService.getMovie(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("After returning from " + joinPoint.getSignature().getName() + " with result " + result);
    }

    @AfterThrowing(value = "execution(* academy.mindswap.services.*Service.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        System.out.println("After throwing from " + joinPoint.getSignature().getName() + " with exception " + ex.getMessage());
    }
}


