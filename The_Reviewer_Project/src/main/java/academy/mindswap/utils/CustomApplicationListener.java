package academy.mindswap.utils;

import academy.mindswap.security.AuthenticationFilter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomApplicationListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private static final Logger LOGGER = LogManager.getLogger(CustomApplicationListener.class);

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        Object userName = event.getAuthentication().getPrincipal();
        LOGGER.log(Level.INFO, LocalDateTime.now() + " || " +  userName + " || " + "Login Failed");
        LogWriter.writeToFile(LocalDateTime.now() + " || " +  userName + " || " + "Successful Login");
    }
}