package se.cygni.calendar.exercises;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class JavaDateTest {

    @SuppressWarnings("deprecation")
    @Test
    public void testDate() throws Exception {
        // 3854-12-31 00:00:00 CET!
        Date date = new Date(1954, 11, 31);
        System.out.println(date);
        // Mutable
        date.setYear(19);
    }

    @Test
    public void testCalendar() throws Exception {
        java.util.Calendar calendar = Calendar.getInstance();
        calendar.set(2013,2,28,13,24,56);

        // GregorianCalendar Thu Mar 28 13:24:56 CET 2013
        String simpleName = calendar.getClass().getSimpleName();
        System.out.println(simpleName + " " + calendar.getTime());


        calendar = Calendar.getInstance(Locale.forLanguageTag("th-TH"));
        calendar.set(2013,2,28,13,24,56);

        // BuddhistCalendar Wed Mar 28 13:24:56 CET 1470
        simpleName = calendar.getClass().getSimpleName();
        System.out.println(simpleName + " " + calendar.getTime());
    }
}
