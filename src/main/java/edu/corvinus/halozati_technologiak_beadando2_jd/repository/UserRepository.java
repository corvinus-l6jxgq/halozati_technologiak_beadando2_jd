package edu.corvinus.halozati_technologiak_beadando2_jd.repository;

import edu.corvinus.halozati_technologiak_beadando2_jd.modell.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}