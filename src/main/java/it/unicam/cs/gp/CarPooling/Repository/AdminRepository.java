package it.unicam.cs.gp.CarPooling.Repository;

import it.unicam.cs.gp.CarPooling.Model.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface AdminRepository extends CrudRepository<Admin, Integer> {
    Optional<Admin> findByEmail(String email);

    Optional<Admin> findByNome(String nome);
}