package babackend.BABackend.DAO;


import babackend.BABackend.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Task, Integer> {

    @Modifying
    @Query("UPDATE Task SET task_owner = null WHERE task_owner = ?1")
    void resetId(int id);

}
