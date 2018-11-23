package babackend.BABackend.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
public class AuthorizationController {
    private final Logger log = LoggerFactory.getLogger(AuthorizationController.class);


    @GetMapping( value="/account/login")
    public Principal user(Principal user) {
        log.info("wlazlo");
        return user;
    }

}
