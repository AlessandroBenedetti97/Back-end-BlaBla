package it.unicam.cs.gp.CarPooling.Response;
import it.unicam.cs.gp.CarPooling.Model.Utente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private Utente utente;
    private String token;
    private String error;
}