package it.unicam.cs.gp.CarPooling.Controller;

import it.unicam.cs.gp.CarPooling.Model.Admin;
import it.unicam.cs.gp.CarPooling.Model.Utente;
import it.unicam.cs.gp.CarPooling.Request.LoginRequest;
import it.unicam.cs.gp.CarPooling.Request.SignUpRequest;
import it.unicam.cs.gp.CarPooling.Response.JwtAuthenticationResponse;
import it.unicam.cs.gp.CarPooling.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping("/createAdmin")
    public ResponseEntity<String> addAdmin(@RequestBody SignUpRequest request) {
        try {
            String result = service.registerAdmin(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'aggiunta dell'utente: " + e.getMessage());
        }
    }

    @PostMapping(value ="/loginAdmin")
    public ResponseEntity<JwtAuthenticationResponse> getLogin(@RequestBody LoginRequest loginRequest){
        try{
            return ResponseEntity.ok(service.signIn(loginRequest));
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Entra sul controller ma non arriva al service");
            return ResponseEntity.badRequest().body(JwtAuthenticationResponse.builder().error("Authentication failed").build());
        }
    }

    
}
