package academy.mindswap.services;

import academy.mindswap.commands.LoginRequest;
import academy.mindswap.exceptions.otherExceptions.AlreadyLoggedInException;
import academy.mindswap.persistence.models.User;
import academy.mindswap.exceptions.otherExceptions.LoginRequestFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private CookiesService cookiesService;

    public User login(LoginRequest loginRequest, HttpServletRequest request){
        Optional<Cookie> cookies = cookiesService.getReviewerCookie(request);
        if (cookies.isPresent()){
            throw new AlreadyLoggedInException();
        }
        return userService.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
    }

    public void logout(){
    }


    public User validate(String email) {
        return userService.validate(email);
    }

    /*@Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }*/
}

