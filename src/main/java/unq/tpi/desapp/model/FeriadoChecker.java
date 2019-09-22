package unq.tpi.desapp.model;

import com.google.gson.Gson;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FeriadoChecker {

    RestTemplate restTemplate = new RestTemplate();
    List feriados;


    public FeriadoChecker(int año) {
        String url = "http://nolaborables.com.ar/api/v2/feriados/" + año;
        this.feriados = restTemplate.getForObject(url, List.class);
    }

    public List<LocalDateTime> filtarDiasHabiles(List<LocalDateTime> dias) {
        return this.sacarFeriados(this.sacarFinesDeSemana(dias));
    }


    public List<LocalDateTime> sacarFeriados(List<LocalDateTime> dias) {
        return dias.stream().filter(dia -> !this.esFeriado(dia))
                .collect(Collectors.toList());
    }


    public List<LocalDateTime> sacarFinesDeSemana(List<LocalDateTime> dias) {
        return dias.stream().filter(dia -> !this.esFinDeSemana(dia))
                .collect(Collectors.toList());
    }


    public boolean esFeriado(LocalDateTime fecha) {
        long count = this.feriados.stream().filter(feriado -> new Gson().toJsonTree(feriado).getAsJsonObject().get("dia").getAsInt() == fecha.getDayOfMonth()
                                           && new Gson().toJsonTree(feriado).getAsJsonObject().get("mes").getAsInt() == fecha.getMonthValue())
                                           .count();
        return count > 0;
    }


    public boolean esFinDeSemana(LocalDateTime fecha) {
        return fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY;
    }
}
