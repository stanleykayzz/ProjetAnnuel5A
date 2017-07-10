package server.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Created by ileossa on 02/07/2017.
 */
@Service
public class DateService {

    @Value("${booking.timezone}")
    public String zoneId = "Africa/Libreville";

    public Date stringToDate(String dateEntry) {
        LocalDate dateLocal = LocalDate.parse(dateEntry);
        // Get current Date + Time + zoneId
        String zoneI = zoneId;    //refuse de passer directement zoneId dans la methodes
        ZoneId zoneId = ZoneId.of(zoneI);

        //convert localDate to Date
        return Date.from(dateLocal.atStartOfDay(zoneId).toInstant());
    }

    public Date currentLocalTime(){
        LocalDate currentDate = LocalDate.now();
        String zoneI = zoneId;    //refuse de passer directement zoneId dans la methodes
        ZoneId zoneId = ZoneId.of(zoneI);
        return Date.from(currentDate.atStartOfDay(zoneId).toInstant());

    }

    public long numberDaysBetween(String startDate, String endDate){
        LocalDate dateBefore = LocalDate.parse(startDate);
        LocalDate dateAfter = LocalDate.parse(endDate);
        return ChronoUnit.DAYS.between(dateBefore, dateAfter);
    }
}