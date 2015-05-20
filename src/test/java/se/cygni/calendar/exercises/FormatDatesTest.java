package se.cygni.calendar.exercises;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQueries;

public class FormatDatesTest {

    @Test
    public void testParseFormat() throws Exception {
        LocalDateTime dateTime = LocalDateTime.parse("2015-05-31T08:15:00");
        System.out.println(dateTime);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 2015-05-31 08:15:00
        System.out.println(dateTime.format(df));

        LocalDateTime dateTime2 = LocalDateTime.parse("2015-05-31 08:15:00", df);
    }

    @Test
    public void testFormat() throws Exception {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss VV");

        ZoneId zoneId = ZoneId.of("Europe/Stockholm");
        System.out.println(df.format(LocalDateTime.parse("2014-01-04T14:33:56").atZone(zoneId)));
    }

    @Test
    public void testParse() throws Exception {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss VV");

        System.out.println(ZonedDateTime.parse("2014-01-04 14:33:56 Europe/Stockholm", df));
    }

    @Test
    public void testParse2() throws Exception {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss VV");
        TemporalAccessor temporalAccessor = df.parse("2014-01-04 14:33:56 Europe/Stockholm");
        System.out.println(temporalAccessor.getClass());
        System.out.println(temporalAccessor);
        System.out.println(temporalAccessor.query(TemporalQueries.zoneId()));
    }

    @Test
    public void testNice() throws Exception {
        // Fluent
        LocalDate today = LocalDate.now();
        LocalDate payday = today.with(TemporalAdjusters.lastDayOfMonth()).minusDays(2);


        // Immutable
        LocalDate dateOfBirth = LocalDate.of(2012, Month.MAY, 14);
        LocalDate firstBirthday = dateOfBirth.plusYears(1);

        // Nice enums
        DayOfWeek thursday = DayOfWeek.MONDAY.plus(3);
        int _29 = Month.FEBRUARY.maxLength();

    }

    @Test
    public void testLocalDateTime() throws Exception {
        // 2015-06-01T08:00:00 local time
        LocalDateTime local = LocalDateTime.of(2015, Month.JUNE, 1, 8, 0);

        // 2015-06-01T08:00+03:00[Europe/Helsinki]
        ZonedDateTime helsinki = local.atZone(ZoneId.of("Europe/Helsinki"));
        System.out.println(helsinki);

        // 2015-06-01T08:00+02:00[Europe/Stockholm]
        ZonedDateTime stockholm = local.atZone(ZoneId.of("Europe/Stockholm"));
        System.out.println(stockholm);

        // 2015-06-01T06:00:00Z
        Instant instant = local.atZone(ZoneId.of("Europe/Stockholm")).toInstant();
        System.out.println(instant);
    }

    @Test
    public void testTimeUS() throws Exception {
        // 2015-06-01T08:00:00 local time
        LocalDateTime local = LocalDateTime.of(2015, Month.JUNE, 1, 8, 0);

        // 2015-06-01T08:00+02:00[Europe/Stockholm]
        ZonedDateTime stockholm = local.atZone(ZoneId.of("Europe/Stockholm"));
        System.out.println(stockholm);

        // 2015-06-01T02:00-04:00[America/New_York]
        ZonedDateTime newYork = stockholm.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(stockholm + " = " + newYork);

        // 2015-06-01T08:00-04:00[America/New_York]
        ZonedDateTime newYork2 = stockholm.withZoneSameLocal(ZoneId.of("America/New_York"));

        // 2015-06-01T14:00+02:00[Europe/Stockholm]
        ZonedDateTime stockholm2 = newYork2.withZoneSameInstant(ZoneId.of("Europe/Stockholm"));
        System.out.println(newYork2 + " = " + stockholm2);
    }
}
