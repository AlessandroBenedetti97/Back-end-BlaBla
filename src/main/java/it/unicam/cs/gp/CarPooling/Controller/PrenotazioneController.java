package it.unicam.cs.gp.CarPooling.Controller;

import it.unicam.cs.gp.CarPooling.Model.GiornoSettimana;
import it.unicam.cs.gp.CarPooling.Model.FasciaOraria;
import it.unicam.cs.gp.CarPooling.Model.Prenotazione;
import it.unicam.cs.gp.CarPooling.Model.Utente;
import it.unicam.cs.gp.CarPooling.Request.BookingRequest;
import it.unicam.cs.gp.CarPooling.Service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/booking")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping(path = "/book")
    public ResponseEntity<String> book(@RequestBody BookingRequest request,
                                       @RequestHeader("Authorization") String token) {
        try {
            String cleanedToken = token.replace("Bearer ", "");
            String result = prenotazioneService.prenota(request, cleanedToken);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'aggiunta della prenotazione: " + e.getMessage());
        }
    }
    @GetMapping(path = "/allBookings")
    public ResponseEntity<Iterable<Prenotazione>> getAllPrenotazioni() {
             Iterable<Prenotazione> prenotazioni = prenotazioneService.findAllPrenotazioni();
             return ResponseEntity.ok(prenotazioni);
        }


    @GetMapping(path = "/booking/{id}")
    public @ResponseBody Prenotazione getPrenotazioneById(@PathVariable Integer id) {
        return prenotazioneService.getPrenotazioneById(id);
    }

    @DeleteMapping(path = "/booking/{id}")
    public @ResponseBody String deletePrenotazione(@PathVariable Integer id) {
        try {
            prenotazioneService.deletePrenotazione(id);
            return "Prenotazione eliminata con successo";
        } catch (Exception e) {
            e.printStackTrace();
            return "Errore durante l'eliminazione della prenotazione: " + e.getMessage();
        }
    }
}
