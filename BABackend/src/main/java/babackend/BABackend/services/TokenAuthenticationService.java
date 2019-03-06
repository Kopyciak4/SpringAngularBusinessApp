package babackend.BABackend.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class TokenAuthenticationService {
    static final int EXPIRE_TIME =  8640000;
    static final String SECRET = "Secret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    static final String ROLE = "role";
    static Logger logger = LoggerFactory.getLogger(TokenAuthenticationService.class);
    static TokenService tokenService2;

    @Autowired
    private TokenService tokenService;

    @PostConstruct
    private void initTokenService() {
        tokenService2 = this.tokenService;
    }

    public static void addAuthentication(HttpServletResponse res, Authentication auth ){
        String JWT = Jwts.builder()
                .setSubject(auth.getName())
                .claim(ROLE , auth.getAuthorities().toArray()[0].toString())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader("access-control-expose-headers", "Authorization");
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    public static Authentication getAuthentication(HttpServletRequest req) throws UnsupportedEncodingException {
        String token = req.getHeader(HEADER_STRING);
        if(token != null) {
            logger.info("MOJ TOKEN: " + token);
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            String user = claims.getSubject();
            if(user == null){
                logger.info("BRAK USERA");
                return null;
            }else {
                String getToken = tokenService2.getToken(user);
                logger.info("CZY JEST TOKEN? " + tokenService2.getToken(user));
                if(getToken == null || !getToken.equals(token) ) {
                    logger.info("NIE JEST NA CZARNEJ LISCIE");
                    List<GrantedAuthority> role = new ArrayList<>();
                    role.add(new SimpleGrantedAuthority(claims.get(ROLE, String.class)));
                    return new UsernamePasswordAuthenticationToken(user, null, role);
                } else {
                    logger.info("JEST NA CZARNEJ LISCIE");
                    return null;
                }
            }
        }
        return null;

    }

    public static void saveTokenToBlacklist(HttpServletRequest req) {

        String token = req.getHeader(HEADER_STRING);

        Claims body = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();

        Date exp = body.getExpiration();
        tokenService2.saveToken( body.getSubject(), token, (exp.getTime() - System.currentTimeMillis()) );


    }


}
