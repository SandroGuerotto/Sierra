package helper;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DateFormatter {

    public DateFormatter(){

    }

    public static String LocalDateToString(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }
}
