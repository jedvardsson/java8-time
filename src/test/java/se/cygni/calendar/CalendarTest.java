package se.cygni.calendar;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class CalendarTest {

    private Calendar calendar;
    private ZoneId stockholm;

    @Before
    public void before() throws Exception {
        calendar = new Calendar();
        stockholm = ZoneId.of("Europe/Stockholm");
        calendar.addActivityTemplate(createAppointment("Watch Donald Duck", "2014-12-24T15:00:00", "2014-12-24T16:00:00", stockholm));

        calendar.addActivityTemplate(createAppointment("Scrum", "2015-01-03T08:00:00", "2015-01-03T08:15:00", stockholm));
        calendar.addActivityTemplate(createAppointment("Dentist", "2015-01-03T10:00:00", "2015-01-03T11:15:00", stockholm));

        calendar.addActivityTemplate(createAppointment("Scrum", "2015-01-04T08:00:00", "2015-01-04T08:15:00", stockholm));
        calendar.addActivityTemplate(createAppointment("Planning", "2015-01-04T09:00:00", "2015-01-04T10:00:00", stockholm));

        calendar.addActivityTemplate(createAppointment("Running", "2015-02-05T17:00:00", "2015-12-24T19:00:00", stockholm));
    }

    @Test
    public void test() throws Exception {
        List<Activity> activities = calendar.getActivities(ZonedDateTime.now(stockholm),
                parse("2015-01-01T00:00:00", stockholm),
                parse("2015-02-01T00:00:00", stockholm)
        );

        for (Activity activity : activities) {
            System.out.println(activity);
        }

    }

    private ZonedDateTime parse(String date, ZoneId zoneId) {
        return LocalDateTime.parse(date).atZone(zoneId);
    }

    private Appointment createAppointment(String title, String start, String end, ZoneId zoneId) {
        Appointment a = new Appointment();
        a.setTitle(title);
        a.setStart(parse(start, zoneId));
        a.setEnd(parse(end, zoneId));
        return a;
    }

}