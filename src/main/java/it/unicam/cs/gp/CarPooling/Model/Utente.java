package it.unicam.cs.gp.CarPooling.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "utente", schema = "dbo")
public class Utente implements UserDetails {

    @Id
    @Column(name = "id_utente")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Il campo username non può essere vuoto")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Il campo password non può essere vuoto")
    @Size(min = 6, message = "La password deve contenere almeno 6 caratteri")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Il campo nome non può essere vuoto")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "Il campo cognome non può essere vuoto")
    @Column(name = "cognome")
    private String cognome;

    @NotBlank
    @Column(name = "email")
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> prenotazioni;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "utente", orphanRemoval = true)
    @JsonManagedReference
    private Set<Prenotazione> barcode = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
