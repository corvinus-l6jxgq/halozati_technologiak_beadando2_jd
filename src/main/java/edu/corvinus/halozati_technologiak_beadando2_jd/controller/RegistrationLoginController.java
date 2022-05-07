package edu.corvinus.halozati_technologiak_beadando2_jd.controller;

import edu.corvinus.halozati_technologiak_beadando2_jd.CeasarCipher;
import edu.corvinus.halozati_technologiak_beadando2_jd.modell.LoginForm;
import edu.corvinus.halozati_technologiak_beadando2_jd.modell.RegistrationForm;
import edu.corvinus.halozati_technologiak_beadando2_jd.modell.User;
import edu.corvinus.halozati_technologiak_beadando2_jd.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.Cipher;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RegistrationLoginController {

    private final Logger logger = LoggerFactory.getLogger(RegistrationLoginController.class);

    private final UserRepository repository;

    @Autowired
    public RegistrationLoginController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String showLoginForm(LoginForm loginForm) {
        return "login";
    }

    @PostMapping("/")
    public String login(Model model, @Valid LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info("Validation errors occurred!");
            model.addAttribute("hiba", "Hiba történt");
            return "login";
        }

        //Regisztráció ellenőrzése
        final boolean userIsRegistered = repository.findByUsername(loginForm.getUsername()).isPresent();
        if (userIsRegistered) {

            //Jelszóellenőrzés
            String passwordGiven = loginForm.getPassword();
            String passwordStoredEncrypted = repository.findByUsername(loginForm.getUsername()).get().getPassword();
            String passwordStored = new CeasarCipher().decipher(passwordStoredEncrypted,5);
            boolean passwordCorrect = passwordGiven.equals(passwordStored);
            if (passwordCorrect) {
                //model.addAttribute("user", repository.findByUsername(loginForm.getUsername()));
                return "welcome";
            } else {
                model.addAttribute("hiba", "Hibás jelszó");
                return "login";
            }

        } else {
            logger.info("Ismeretlen felhasználónév");
            model.addAttribute("hiba", "Ismeretlen felhasználónév");
            return "login";
        }
    }

    @GetMapping("/registration")
    public String showRegistrationForm(RegistrationForm registrationForm) {
        return "registration";
    }

    @PostMapping("/registration")
    public String register(Model model, @Valid RegistrationForm registrationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info("Validation errors occurred!");
            return "registration";
        }

        logger.info("Registering user with username: {}", registrationForm.getUsername());
        final boolean userIsRegistered = repository.findByUsername(registrationForm.getUsername()).isPresent();
        if (!userIsRegistered) {
            User user = new User();
            user.setUsername(registrationForm.getUsername());
            user.setFullname(registrationForm.getFullname());
            user.setPassword(new CeasarCipher().cipher(registrationForm.getPassword(),5));
            repository.save(user);
            return "redirect:/welcome?username=" + registrationForm.getUsername();
        }
        model.addAttribute("hiba", "A felhasználónév foglalt.");
        return "registration";
    }

    @GetMapping("/welcome")
    public String showWelcomePage(@RequestParam(required = false) String username, Model model) {
        final Optional<User> foundUser = repository.findByUsername(username);
        if (foundUser.isEmpty()) {
            return "redirect:/";
        }

        final User user = foundUser.get();
        repository.save(user);

        return "welcome";
    }

}
