package babackend.BABackend.DAO;

import babackend.BABackend.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Integer> {
    Account findByUserLogin(String login);
}

