package babackend.BABackend.filters;



import babackend.BABackend.services.TokenAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    Logger logger = LoggerFactory.getLogger(JWTLoginFilter.class);
    public JWTLoginFilter(AntPathRequestMatcher url, AuthenticationManager authManager) {
        super(url);
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {
        String authorization = req.getHeader(HttpHeaders.AUTHORIZATION);
        String encodedCredentials = authorization.substring("Basic".length()).trim();
        byte[] decodedCredentials = Base64.getDecoder().decode(encodedCredentials);
        String credentials = new String(decodedCredentials);
        String[] data = credentials.split(":");
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        data[0],
                        data[1],
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        Map<String, String> map = new HashMap<>();
        map.put("role", auth.getAuthorities().toArray()[0].toString());


        TokenAuthenticationService.addAuthentication(res, auth);
        res.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }
}
