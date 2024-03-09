package it.unicam.cs.gp.CarPooling.Service;

import com.azure.core.annotation.HeaderParam;
import it.unicam.cs.gp.CarPooling.Jwt.JwtServiceInterface;
import it.unicam.cs.gp.CarPooling.Model.*;
import it.unicam.cs.gp.CarPooling.Repository.PrenotazioneRepository;
import it.unicam.cs.gp.CarPooling.Repository.UtenteRepository;
import it.unicam.cs.gp.CarPooling.Request.BookingRequest;
import it.unicam.cs.gp.CarPooling.Request.SignUpRequest;
import org.apache.qpid.proton.codec.security.SaslOutcomeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private JwtServiceInterface jwtService;
    @Autowired
    private UtenteRepository utenteRepository;

    public String prenota(BookingRequest bookingRequest, String token) {


        // prendi token e deserializza token
        // prendi mail
        // query che getta l'utente dalla mail -> prendi id
        // Deserializza il token per ottenere le informazioni sull'utente
        String userEmail = jwtService.extractUserName(token);

        // Esegue la query per ottenere l'utente basato sull'email
        Utente utente = utenteRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(utente);
        prenotazione.setFasciaOrariaPrenotazione(bookingRequest.getFascia_oraria_prenotazione());
        prenotazione.setGiornoSettimana(bookingRequest.getGiorno_prenotazione());

        prenotazioneRepository.save(prenotazione);
        return "tutto ok ";
    }
    public Iterable<Prenotazione> findAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione getPrenotazioneById(Integer id) {
        return prenotazioneRepository.findById(id).orElse(null);
    }

    public void deletePrenotazione(Integer id) {
        prenotazioneRepository.deleteById(id);
    }

    // Altri metodi per la gestione delle prenotazioni
}
