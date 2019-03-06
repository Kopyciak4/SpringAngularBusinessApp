package babackend.BABackend.services;

import babackend.BABackend.DAO.AccountsRepository;
import babackend.BABackend.DAO.TasksRepository;
import babackend.BABackend.domain.Account;
import babackend.BABackend.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountsRepository accountsRepository;
    private PasswordEncoder passwordEncoder;
    private TaskService taskService;
    
    @Autowired
    public AccountServiceImpl(AccountsRepository accountsRepository, TaskService taskService){
        this.accountsRepository = accountsRepository;
        this.taskService = taskService;
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
            for (Task task : account.getUser().getTasks()) {
                task.setTaskOwner(null);
            }
        }
       return accounts;
    }

    @Override
    public void register(Account account){
        encryptPassword(account);
        accountsRepository.save(account);
    }

    @Override
    public Account getAccount(String login) {
        Account account = accountsRepository.findByUserLogin(login);
        if(account == null)
            return null;
        account.getUser().setPassword("");
        for (Task task : account.getUser().getTasks()) {
            task.setTaskOwner(null);
        }
        return account;
    }

    @Override
    public void updateAccount(Account account) {
        if(account.getUser().getPassword().equals("")) {
            account.getUser().setPassword(
                    accountsRepository.findById(account.getAccountID()).get().getUser().getPassword()
            );
        }else {
            encryptPassword(account);
        }
        accountsRepository.save(account);
    }

    @Override
    @Transactional
    public void deleteAccount(int accountID) {
        taskService.resetUserId(accountsRepository.findById(accountID).get().getUser().getUserID());
        accountsRepository.deleteById(accountID);

    }

    private void encryptPassword(Account account) {
        account.getUser().setPassword( passwordEncoder.encode(account.getUser().getPassword()));
    } 
}
