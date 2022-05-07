package edu.corvinus.halozati_technologiak_beadando2_jd.modell;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String username;

    private String fullname;

    private String password;

    public User() {

    }

    public User(String username) {
        this.username = username;
    }

    public User(long id, String username) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
    }

    public String getUsername() {return username;}

    public void setUsername(String username) { this.username = username; }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
