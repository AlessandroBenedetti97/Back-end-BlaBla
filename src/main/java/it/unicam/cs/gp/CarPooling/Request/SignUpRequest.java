package it.unicam.cs.gp.CarPooling.Request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SignUpRequest implements Serializable {

    @JsonProperty("nome")
    public String nome;
    @JsonProperty("cognome")
    public String cognome;
    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    public String password;

}
