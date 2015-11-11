package se.cygni.calendar;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class BirthDateTest {

    @Test
    public void test() throws Exception {
        BirthDate birthDate = new BirthDate();
        birthDate.setTitle("Jon");
        birthDate.setLocalDate(LocalDate.of(1973, 6, 12));

        List<Activity> activities = birthDate.getActivities(
                ZonedDateTime.now(),
                LocalDate.of(2012, 7, 8).atStartOfDay(ZoneId.of("Europe/Stockholm")),
                LocalDate.of(2015, 7, 8).atStartOfDay(ZoneId.of("Europe/Stockholm")));
        System.out.println(activities);
    }
}