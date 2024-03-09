package it.unicam.cs.gp.CarPooling.Repository;

import it.unicam.cs.gp.CarPooling.Model.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UtenteRepository extends CrudRepository<Utente, Integer> {
    Utente findByUsername(String username);
    Optional<Utente> findByEmail(String email);

    Optional<Utente> findByNome(String nome);
}

