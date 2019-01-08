package babackend.BABackend.web;

import babackend.BABackend.domain.Account;
import babackend.BABackend.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProfileController {

    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid  @RequestBody Account account){
      accountService.register(account);
    }



}
