package babackend.BABackend.web;


import babackend.BABackend.domain.Account;
import babackend.BABackend.domain.User;
import babackend.BABackend.services.AccountService;
import babackend.BABackend.services.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AccountController {

    AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts(){
       return accountService.getAccounts();

    }

    @GetMapping("/accounts/{login}")
    public Account getAccount(
            @PathVariable String login){
        return accountService.getAccount(login);
    }

}
