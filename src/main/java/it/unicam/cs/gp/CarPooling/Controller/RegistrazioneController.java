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
@Controller
@RequestMapping("/registration")
public class RegistrazioneController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String registrationForm(Model model) {
        model.addAttribute("utente", new Utente());
        return "registration";
    }

    @PostMapping
    public String registrationSubmit(@ModelAttribute Utente utente) {
        utenteService.registerUtente(utente);
        return "redirect:/login";
    }
}
