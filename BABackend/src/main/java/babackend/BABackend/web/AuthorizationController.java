package babackend.BABackend.web;


import org.springframework.web.bind.annotation.*;




@RestController
public class AuthorizationController {

    @PostMapping("/account/daj")
    public boolean postData(){
        return true;
    }
}

