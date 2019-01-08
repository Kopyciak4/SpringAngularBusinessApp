package babackend.BABackend.services;

import babackend.BABackend.DAO.AccountsRepository;
import babackend.BABackend.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountsRepository accountsRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountsRepository accountsRepository){
        this.accountsRepository = accountsRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<Account> getAccounts(){

        List<Account> accounts = accountsRepository.findAll();

        for (Account account : accounts) {
            account.getUser().setPassword("");
        }
       return accounts;
    }

    @Override
    public void register(Account account){
        account.getUser().setPassword( passwordEncoder.encode(account.getUser().getPassword()));
        accountsRepository.save(account);
    }

    @Override
    public Account getAccount(String login) {
        Account account = accountsRepository.findByUserLogin(login);
        if(account == null)
            return null;
        account.getUser().setPassword("");
        return account;
    }
}
