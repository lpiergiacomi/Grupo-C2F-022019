package unq.tpi.desapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feriado {
    private String motivo;
    private String tipo;
    private int dia;
    private int mes;
    private int id;

}
