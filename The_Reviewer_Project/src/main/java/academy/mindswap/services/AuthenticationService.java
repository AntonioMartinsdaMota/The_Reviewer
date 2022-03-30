package academy.mindswap.services;

import academy.mindswap.commands.LoginRequest;
import academy.mindswap.persistence.models.User;
import academy.mindswap.persistence.repositories.exceptions.LoginRequestFailedException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {


    @Autowired
    private UserService userService;

    public User login(LoginRequest loginRequest) throws LoginRequestFailedException {
        return userService.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
    }

    public void logout(){

    }


    public User validate(String email) {
        return userService.validate(email);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

