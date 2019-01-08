package babackend.BABackend.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Account implements Serializable {


    @NotNull
    @Size(min=1, max=25)
    private String name;
    @NotNull
    @Size(min=1, max=40)
    private String adress;
    @NotNull
    @Size(min=1, max=40)
    @Email
    private String email;
    @NotNull
    @Size(min=1, max=25)
    private String surname;
    @Id
    private int accountID;


    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "loginDetailsId")
    private User user;




    public Account() {}

    public String getName(){

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getAdress(){

        return adress;
    }

    public void setAdress(String adress) {

        this.adress = adress;
    }

    public String getEmail(){

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname) {

        this.surname = surname;
    }

    public int getAccountID(){
        return  accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public User getUser(){
        return  user;
    }

    public void setUser(User user) {
        this.user = user;
    }





}
