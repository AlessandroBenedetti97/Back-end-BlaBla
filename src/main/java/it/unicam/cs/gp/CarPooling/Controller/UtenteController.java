package it.unicam.cs.gp.CarPooling.Controller;

import it.unicam.cs.gp.CarPooling.Model.Utente;
import it.unicam.cs.gp.CarPooling.Request.LoginRequest;
import it.unicam.cs.gp.CarPooling.Request.SignUpRequest;
import it.unicam.cs.gp.CarPooling.Response.JwtAuthenticationResponse;
import it.unicam.cs.gp.CarPooling.Service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/user")
public class UtenteController {

    @Autowired
    private UtenteService service;

    @PostMapping("/createUser")
    public ResponseEntity<String> addUtente(@RequestBody SignUpRequest request) {
        try {
            String result = service.registerUtente(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'aggiunta dell'utente: " + e.getMessage());
        }
    }

    @PostMapping(value ="/login")
    public ResponseEntity<JwtAuthenticationResponse> getLogin(@RequestBody LoginRequest loginRequest){
        try{
            return ResponseEntity.ok(service.signIn(loginRequest));
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Entra sul controller ma non arriva al service");
            return ResponseEntity.badRequest().body(JwtAuthenticationResponse.builder().error("Authentication failed").build());
        }
    }

    @GetMapping("/getUsers")
    public ResponseEntity<Iterable<Utente>> getAllUtenti() {
        Iterable<Utente> utenti = service.findAllUtenti();
        return ResponseEntity.ok(utenti);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeUtente(@PathVariable Integer id) {
        try {
            service.deleteUtente(id);
            return ResponseEntity.ok("Utente rimosso con successo");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante la rimozione dell'utente: " + e.getMessage());
        }
    }
}
