package nl.rhofman.mpj.service.ping.boundary;

import jakarta.json.bind.annotation.JsonbDateFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Period {
    @JsonbDateFormat(value="yyyy-MM-dd HH:mm:ss", locale = "Locale.ENGLISH")
    public LocalDateTime dateFrom;
    @JsonbDateFormat(value="yyyy-MM-dd", locale = "Locale.ENGLISH")
    public LocalDate dateTo;

    @Override
    public String toString() {
        return "Period{" +
                "dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
