package babackend.BABackend.filters;

import babackend.BABackend.services.TokenAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {


    @Override
    public void doFilter(
            ServletRequest req,
            ServletResponse res,
            FilterChain filterChain)
            throws IOException, ServletException {

        Authentication authentication = null;

        try {
            authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) req);

        } catch (Exception e) {
            ((HttpServletResponse) res).setStatus(HttpStatus.UNAUTHORIZED.value());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(req, res);
    }
}
