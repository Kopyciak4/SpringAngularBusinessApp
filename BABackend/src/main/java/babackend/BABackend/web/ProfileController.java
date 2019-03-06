package babackend.BABackend.web;

import babackend.BABackend.domain.Account;
import babackend.BABackend.services.AccountService;
import babackend.BABackend.validation.EmptyPasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;

@RestController
public class ProfileController {

    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/account/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Validated({EmptyPasswordValidation.class, Default.class}) @RequestBody Account account){
      accountService.register(account);
    }



}
