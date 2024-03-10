package it.unicam.cs.gp.CarPooling.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private GiornoSettimana giornoSettimana;

    @Enumerated(EnumType.STRING)
    private FasciaOraria fasciaOrariaPrenotazione;


    @OneToOne
    @JoinColumn(name = "id_utente", referencedColumnName = "id_utente")
    private Utente utente;

    // Costruttori, Getter e Setter

    public Prenotazione() {
        // Costruttore vuoto richiesto da JPA
    }

    public Prenotazione(Utente utente, GiornoSettimana giornoSettimana, FasciaOraria fasciaOrariaPrenotazione) {
        this.utente = utente;
        this.giornoSettimana = giornoSettimana;
        this.fasciaOrariaPrenotazione = fasciaOrariaPrenotazione;
    }

    // Metodi Getter e Setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GiornoSettimana getGiornoSettimana() {
        return giornoSettimana;
    }

    public void setGiornoSettimana(GiornoSettimana giornoSettimana) {
        this.giornoSettimana = giornoSettimana;
    }

    public FasciaOraria getFasciaOrariaPrenotazione() {
        return fasciaOrariaPrenotazione;
    }

    public void setFasciaOrariaPrenotazione(FasciaOraria fasciaOrariaPrenotazione) {
        this.fasciaOrariaPrenotazione = fasciaOrariaPrenotazione;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
