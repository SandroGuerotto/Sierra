package helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Helperclass to format LocalDate and LocalDateTime.
 * can not be instantiate. All methods are static
 * @author Sandro Guerotto
 * @since 25.12.2016
 * @version 0.1
 */
public abstract class DateFormatter {

	/**
	 * converts LocalDate to a String in format dd.MM.yyyy
	 * @param date LocalDate that has to be converted to a String
	 * @return formatted String value of parameter date
	 */
    public static String LocalDateToString(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    /**
	 * converts LocalDateTime to a String in format dd.MM.yyyy HH:mm
	 * @param date LocalDateTime that has to be converted to a String
	 * @return formatted String value of parameter date
	 */
    public static String LocalDateTimeToString(LocalDateTime date){
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }
    
    /**
	 * converts String to LocalDateTime in format dd.MM.yyyy HH:mm
	 * @param date String that has to be converted to LocalDateTime
	 * @return converted LocalDateTime of parameter date
	 */
    public static LocalDateTime StringToLocalDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return LocalDateTime.parse(date, formatter);
    }
    
    /**
     * calculates difference in days from a date range
     * @param start LocalDateTime
     * @param end   LocalDateTime
     * @return difference from start and end in days as int
     */
    public static int differenceInDays(LocalDateTime start, LocalDateTime end) {
        LocalDate startDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }
    /**
     * calculates difference in hours from a date range
     * @param start LocalDateTime
     * @param end   LocalDateTime
     * @return difference from start and end in hours as int
     */
    public static int differenceInHours(LocalDateTime start, LocalDateTime end) {
        LocalTime startDate = start.toLocalTime();
        LocalTime endDate = end.toLocalTime();
        return (int) ChronoUnit.HOURS.between(startDate, endDate);
    }
}
