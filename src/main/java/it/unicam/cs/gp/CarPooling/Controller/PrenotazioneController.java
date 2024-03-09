package it.unicam.cs.gp.CarPooling.Controller;

import it.unicam.cs.gp.CarPooling.Model.GiornoSettimana;
import it.unicam.cs.gp.CarPooling.Model.FasciaOraria;
import it.unicam.cs.gp.CarPooling.Model.Prenotazione;
import it.unicam.cs.gp.CarPooling.Service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/CarPooling")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping(path = "/book")
    public @ResponseBody String book(@RequestParam Integer utenteId,
                                     @RequestParam GiornoSettimana giornoSettimana,
                                     @RequestParam FasciaOraria fasciaOrariaPrenotazione) {
        try {
            return prenotazioneService.prenota(utenteId, giornoSettimana, fasciaOrariaPrenotazione);
        } catch (Exception e) {
            e.printStackTrace();
            return "Errore durante la prenotazione: " + e.getMessage();
        }
    }
    @GetMapping(path = "/allBookings")
    public @ResponseBody Iterable<Prenotazione> getAllPrenotazioni() {
        return prenotazioneService.findAllPrenotazioni();
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
