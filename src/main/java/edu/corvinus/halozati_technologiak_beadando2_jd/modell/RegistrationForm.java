package edu.corvinus.halozati_technologiak_beadando2_jd.modell;

import edu.corvinus.halozati_technologiak_beadando2_jd.CeasarCipher;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RegistrationForm {

    @Length(min = 6)
    @NotEmpty
    private String username;

    @NotEmpty
    private String fullname;

    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-zA-z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

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
