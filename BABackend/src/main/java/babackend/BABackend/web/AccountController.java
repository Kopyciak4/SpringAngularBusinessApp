package babackend.BABackend.web;


import babackend.BABackend.domain.Account;
import babackend.BABackend.domain.User;
import babackend.BABackend.services.AccountService;
import babackend.BABackend.services.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public List<Account> getAccounts(){
       return accountService.getAccounts();

    }
    @PreAuthorize("#login == authentication.principal || hasRole('ROLE_ADMIN')")
    @GetMapping("/{login}")
    public Account getAccount(
            @PathVariable String login){
        return accountService.getAccount(login);
    }
    @PreAuthorize("#account.user.login == authentication.principal || hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public void updateAccount(@Validated @RequestBody Account account) {
        accountService.updateAccount(account);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping()
    public void deleteAccount(@RequestParam("accountID") int accountID) {
        accountService.deleteAccount(accountID);
    }



}
