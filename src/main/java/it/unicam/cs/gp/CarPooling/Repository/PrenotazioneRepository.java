package it.unicam.cs.gp.CarPooling.Repository;

import it.unicam.cs.gp.CarPooling.Model.Prenotazione;
import it.unicam.cs.gp.CarPooling.Model.Utente;
import it.unicam.cs.gp.CarPooling.Model.GiornoSettimana;
import it.unicam.cs.gp.CarPooling.Model.FasciaOraria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Integer> {

    boolean existsByUtenteAndGiornoSettimanaAndFasciaOrariaPrenotazione(
            Utente utente,
            GiornoSettimana giornoSettimana,
            FasciaOraria fasciaOrariaPrenotazione);

    @Query("SELECT COUNT(p) FROM Prenotazione p " +
            "WHERE p.giornoSettimana = :giornoSettimana " +
            "AND p.fasciaOrariaPrenotazione = :fasciaOrariaPrenotazione ")

    long countByGiornoSettimanaAndFasciaOrariaPrenotazione(
            @Param("giornoSettimana") GiornoSettimana giornoSettimana,
            @Param("fasciaOrariaPrenotazione") FasciaOraria fasciaOrariaPrenotazione);
}
