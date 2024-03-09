package it.unicam.cs.gp.CarPooling.Request;


import com.fasterxml.jackson.annotation.JsonProperty;
import it.unicam.cs.gp.CarPooling.Utils.JsonUtils;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.io.Serializable;


@Getter
@Setter
public class UserRequest implements Serializable {

    @JsonProperty("email")
    public String email;

    public String toJson(){
        return  JsonUtils.toJson(this);
    }

}
