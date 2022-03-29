package academy.mindswap.lms.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Aspect
@Component
public class LoggingAspect {







   // MsLogger logger = new MsLogger();

    @Pointcut("@annotation( academy.mindswap.lms.annotations.MindswapAnnotation)")
    public void mindswapAnnotation() {}

    @Around("mindswapAnnotation()")
    public Object logAroundAnnotation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("MindswapAnnotation - Around Before:" + proceedingJoinPoint.getSignature());
        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        }
        System.out.println("MindswapAnnotation - Around After:" + proceedingJoinPoint.getSignature());
        return result;
    }




    @Around(value="execution(* academy.mindswap.lms.services.*Service.getUserById(..))")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Around Before:" + proceedingJoinPoint.getSignature());
        Object result;
        try {
             result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        }
        System.out.println("Around After:" + proceedingJoinPoint.getSignature());
        return result;
    }


    @Before(value="execution(*..*List<*..*UserDto> *.mindswap.lms.services.*Service.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before:" + joinPoint.getSignature());
    }

    @After(value="execution(* academy.mindswap.lms.services.*Service.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After:" + joinPoint.getSignature());
    }

    @AfterReturning(value="execution(* academy.mindswap.lms.services.*Service.getUserById(..))", returning="result")
    public void logAfterReturning(JoinPoint joinPoint, Optional result) {
        System.out.println("AfterReturning:" + joinPoint.getSignature() + " " + result.get());
    }

    @AfterThrowing(value="execution(* academy.mindswap.lms.services.*Service.*(..))", throwing="ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        System.out.println("AfterThrowing:" + joinPoint.getSignature() + " " + ex.getMessage());
    }
}
