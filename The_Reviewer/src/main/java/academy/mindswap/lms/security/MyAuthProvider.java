package academy.mindswap.lms.security;


import academy.mindswap.lms.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MyAuthProvider {

    private AuthenticationService authenticationService;

    public Authentication validateEmail(String email) {
        return new UsernamePasswordAuthenticationToken(authenticationService.validate(email), null, new ArrayList<>());
    }



    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
}
