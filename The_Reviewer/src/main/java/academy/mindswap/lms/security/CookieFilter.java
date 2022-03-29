package academy.mindswap.lms.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

public class CookieFilter extends OncePerRequestFilter {

    public static final String AUTH_COOKIE = "oreo";

    private MyAuthProvider authProvider;

    public CookieFilter(MyAuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> optionalCookie = Stream.of(
                Optional.ofNullable(cookies)
                        .orElse(new Cookie[0])
                )
                .filter(cookie -> cookie.getName().equals(AUTH_COOKIE))
                .findFirst();
        try {
            if (optionalCookie.isPresent()) {
                SecurityContext context = SecurityContextHolder.getContext();
                String email = optionalCookie.get().getValue();
                context.setAuthentication(authProvider.validateEmail(email));
            }
        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }


}
