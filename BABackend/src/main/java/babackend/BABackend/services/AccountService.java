package babackend.BABackend.services;

import babackend.BABackend.domain.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    List<Account> getAccounts();

    void register(Account account);

    Account getAccount(String login);

}

