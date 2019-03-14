package babackend.BABackend.domain;

import babackend.BABackend.validation.EmptyPasswordValidation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable {


    @NotNull
    @Size(min=1, max=30)
    @Column(nullable = false, unique = true)
    private String login;
    @NotNull
    @NotEmpty(groups = {EmptyPasswordValidation.class})
    @Size(max=60)
    @Column(nullable = false)
    private String password;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int userID;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "taskOwner")
    private List<Task> tasks;

    @Column()
    private int roleId;




    public User(){

    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public int getUserID(){
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}