package academy.mindswap.services;

import academy.mindswap.persistence.models.User;
import academy.mindswap.exceptions.notFoundExceptions.CookieNotFoundException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.stream.Stream;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
public class CookiesService {

    public static final String AUTH_COOKIE = "TheReviewerCookie";

    public ResponseCookie createCookie(User user) {

        ResponseCookie cookie = ResponseCookie
                .from(AUTH_COOKIE, user.getEmail())
                .secure(false)
                .httpOnly(true)
                .path("/")
                .maxAge(60 * 60 * 24)
                .build();
        return cookie;
    }

    public ResponseCookie createLogOutCookie() {

        ResponseCookie cookie = ResponseCookie
                .from(AUTH_COOKIE, "")
                .secure(false)
                .httpOnly(true)
                .path("/")
                .maxAge(0)
                .build();
        return cookie;
    }

    public String getEmailFromCookie(HttpServletRequest request) {

        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String email = decodedJWT.getSubject();

            return email;
        }
        return "@";
    }

    public Optional<Cookie> getReviewerCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
       return Stream.of(
                        Optional.ofNullable(cookies)
                                .orElse(new Cookie[0])
                )
                .filter(cookie -> cookie.getName().equals(AUTH_COOKIE))
                .findFirst();


    }
}
