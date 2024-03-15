package it.unicam.cs.gp.CarPooling.Controller;

import it.unicam.cs.gp.CarPooling.Model.Utente;
import it.unicam.cs.gp.CarPooling.Service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Questa classe gestisce le richieste relative alla registrazione degli utenti nel sistema CarPooling.
 */
@Controller
@RequestMapping("/registration")
public class RegistrazioneController {

    @Autowired
    private UtenteService utenteService;
    /**
     * Restituisce il form di registrazione.
     *
     * @param model il modello per aggiungere l'utente al form
     * @return la pagina di registrazione
     */
    @GetMapping
    public String registrationForm(Model model) {
        model.addAttribute("utente", new Utente());
        return "registration";
    }
    /**
     * Gestisce la sottomissione del form di registrazione.
     *
     * @param utente l'utente registrato
     * @return il reindirizzamento alla pagina di login
     */
    @PostMapping
    public String registrationSubmit(@ModelAttribute Utente utente) {
        utenteService.registerUtente(utente);
        return "redirect:/login";
    }
}
