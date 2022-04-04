package academy.mindswap.aspect;


import academy.mindswap.utils.CustomApplicationListener;
import academy.mindswap.utils.LogWriter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.Optional;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    @AfterThrowing(value = "execution(* academy.mindswap.services.*Service.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        LOGGER.log(Level.INFO,LocalDateTime.now() + " -After throwing from " + joinPoint.getSignature().getName() + " with exception " + ex.getMessage() + "\n");
        LogWriter.writeToFile(LocalDateTime.now() + " -After throwing from " + joinPoint.getSignature().getName() + " with exception " + ex.getMessage() + "\n");
    }
}


