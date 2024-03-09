package it.unicam.cs.gp.CarPooling.Service;

import it.unicam.cs.gp.CarPooling.Model.*;
import it.unicam.cs.gp.CarPooling.Repository.PrenotazioneRepository;
import it.unicam.cs.gp.CarPooling.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    public String prenota(Integer utenteId, GiornoSettimana giornoSettimana, FasciaOraria fasciaOraria) {
        Utente utente = utenteRepository.findById(utenteId).orElse(null);

        if (utente == null) {
            return "Utente non trovato";
        }

        boolean isPrenotato = prenotazioneRepository.existsByUtenteAndGiornoSettimanaAndFasciaOrariaPrenotazione(
                utente, giornoSettimana, fasciaOraria);

        if (isPrenotato) {
            return "Utente già prenotato per quell'orario";
        }

        int postiDisponibili = 4;
        long prenotazioniConteggio = prenotazioneRepository.countByGiornoSettimanaAndFasciaOrariaPrenotazione(
                giornoSettimana, fasciaOraria);

        if (prenotazioniConteggio >= postiDisponibili) {
            return "Posti esauriti per quell'orario";
        }

        Prenotazione prenotazione = new Prenotazione(utente, giornoSettimana, fasciaOraria);
        prenotazioneRepository.save(prenotazione);

        return "Prenotazione effettuata con successo";
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
