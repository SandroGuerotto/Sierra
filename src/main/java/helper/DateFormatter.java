package helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class DateFormatter {

    public DateFormatter(){

    }

    public static String LocalDateToString(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String LocalDateTimeToString(LocalDateTime date){
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public static LocalDateTime StringToLocalDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return LocalDateTime.parse(date, formatter);
    }

    public static int differenceInDays(LocalDateTime start, LocalDateTime end) {
        LocalDate startDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }
    public static int differenceInHours(LocalDateTime start, LocalDateTime end) {
        LocalTime startDate = start.toLocalTime();
        LocalTime endDate = end.toLocalTime();
        return (int) ChronoUnit.HOURS.between(startDate, endDate);
    }
}
