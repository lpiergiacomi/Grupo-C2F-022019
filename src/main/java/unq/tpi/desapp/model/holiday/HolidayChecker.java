package unq.tpi.desapp.model.holiday;

import com.google.gson.Gson;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class HolidayChecker {

    RestTemplate restTemplate = new RestTemplate();
    List holidays;


    public HolidayChecker(int year) {
        String url = "http://nolaborables.com.ar/api/v2/feriados/" + year;
        this.holidays = restTemplate.getForObject(url, List.class);
    }

    public List<LocalDateTime> filterWorkingDays(List<LocalDateTime> days) {
        return this.removeHolidays(this.removeWeekends(days));
    }


    public List<LocalDateTime> removeHolidays(List<LocalDateTime> days) {
        return days.stream().filter(day -> !this.isHoliday(day))
                .collect(Collectors.toList());
    }


    public List<LocalDateTime> removeWeekends(List<LocalDateTime> days) {
        return days.stream().filter(day -> !this.isWeekend(day))
                .collect(Collectors.toList());
    }


    public boolean isHoliday(LocalDateTime date) {
        long count = this.holidays.stream().filter(holiday -> new Gson().toJsonTree(holiday).getAsJsonObject().get("dia").getAsInt() == date.getDayOfMonth()
                && new Gson().toJsonTree(holiday).getAsJsonObject().get("mes").getAsInt() == date.getMonthValue())
                .count();
        return count > 0;
    }


    public boolean isWeekend(LocalDateTime date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }
}
