package it.unicam.cs.gp.CarPooling.Service;

import it.unicam.cs.gp.CarPooling.Model.Role;
import it.unicam.cs.gp.CarPooling.Request.SignUpRequest;
import it.unicam.cs.gp.CarPooling.Jwt.JwtServiceInterface;
import it.unicam.cs.gp.CarPooling.Model.Utente;
import it.unicam.cs.gp.CarPooling.Repository.UtenteRepository;
import it.unicam.cs.gp.CarPooling.Request.LoginRequest;
import it.unicam.cs.gp.CarPooling.Response.JwtAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtenteService implements UserDetailsService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UtenteRepository repository;
    @Autowired
    private JwtServiceInterface jwtServiceInterface;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUtente(SignUpRequest registerRequest) {

        Utente utente = new Utente();
        utente.setNome(registerRequest.getNome());
        utente.setCognome(registerRequest.getCognome());
        utente.setEmail(registerRequest.getEmail());
        utente.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // Aggiungi le proprietà mancanti o modifica se necessario
        utente.setRole(Role.USER);

        repository.save(utente);
        return "tutto ok ";
    }


    public JwtAuthenticationResponse signIn(LoginRequest request) {
        System.out.println("Entra");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        System.out.println("Metà metodo del service");
        Utente user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String jwt = jwtServiceInterface.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }



public String addUtente(String nome, String cognome) {
    Utente utente = new Utente();
    utente.setNome(nome);
    utente.setCognome(cognome);
    repository.save(utente);
    return "Utente salvato con successo";
}

public String deleteUtente(Integer id) {
    repository.deleteById(id);
    return "Utente rimosso con successo";
}

public Iterable<Utente> findAllUtenti() {
    return repository.findAll();
}

public void registerUtente(Utente utente) {
    String encodedPassword = passwordEncoder().encode(utente.getPassword());
    utente.setPassword(encodedPassword);
    repository.save(utente);
}

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Utente utente = repository.findByUsername(username);
    if (utente == null) {
        throw new UsernameNotFoundException("Utente non trovato: " + username);
    }

    return User.builder()
            .username(utente.getUsername())
            .password(utente.getPassword())
            .roles("USER")
            .build();
}

private PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
}
