package unq.tpi.desapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Holiday {
    private String reason;
    private String type;
    private int day;
    private int month;
    private int id;
}
