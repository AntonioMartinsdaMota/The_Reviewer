package academy.mindswap.controllers;


import academy.mindswap.commands.LoginRequest;
import academy.mindswap.persistence.models.User;
import academy.mindswap.services.AuthenticationService;
import academy.mindswap.services.CookiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private CookiesService cookiesService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){

        User user = authService.login(loginRequest);
        if (Objects.isNull(user)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        ResponseCookie cookie = cookiesService.createCookie(user);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE,cookie.toString())
                .body(user);

    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {

        ResponseCookie cookie = cookiesService.createLogOutCookie();

        authService.logout();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE,cookie.toString())
                .build();
    }
}

