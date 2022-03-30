package academy.mindswap.services;

import academy.mindswap.persistence.models.User;
import academy.mindswap.persistence.repositories.exceptions.CookieNotFoundException;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CookiesService {

    public static final String AUTH_COOKIE = "TheReviewerCookie";

    public ResponseCookie createCookie(User user) {

        ResponseCookie cookie = ResponseCookie
                .from(AUTH_COOKIE, user.getUserId().toString())
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

    public Integer getIdFromCookie(HttpServletRequest request)
            throws CookieNotFoundException {
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> optionalCookie = Stream.of(
                        Optional.ofNullable(cookies)
                                .orElse(new Cookie[0])
                )
                .filter(cookie -> cookie.getName().equals(AUTH_COOKIE))
                .findFirst();

            if (optionalCookie.isEmpty()) {
                throw new CookieNotFoundException();
            }

        return Integer.parseInt( optionalCookie.get().getValue());
    }
}
