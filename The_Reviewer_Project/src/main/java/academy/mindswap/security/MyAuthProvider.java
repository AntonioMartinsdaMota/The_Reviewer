package academy.mindswap.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MyAuthProvider {

    /*private AuthenticationService authenticationService;

    public Authentication validateID(Integer id) {
        return new UsernamePasswordAuthenticationToken(authenticationService.validate(id), null, new ArrayList<>());
    }



    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }*/
}
