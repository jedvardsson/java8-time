package se.cygni.calendar;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class AppointmentTest {

    @Test
    public void testGetActivities() throws Exception {
        Appointment a = new Appointment();
        ZoneId stockholm = ZoneId.of("Europe/Stockholm");
        ZoneId helsinki = ZoneId.of("Europe/Helsinki");
        a.setTitle("Scrum");
        a.setStart(parse("2015-05-06T08:00:00", stockholm));
        a.setEnd(parse("2015-05-06T09:00:00", stockholm));

        List<Activity> activities = a.getActivities(
                parse("2015-04-07T00:00:00", helsinki),
                parse("2015-05-06T09:15:00", helsinki),
                parse("2015-06-06T10:15:00", helsinki));

        assertEquals(1, activities.size());
        assertSame(a, activities.get(0).getActivityTemplate());
        assertEquals("Scrum", activities.get(0).getTitle());
        assertEquals(parse("2015-05-06T09:00:00", helsinki), activities.get(0).getStart());
        assertEquals(parse("2015-05-06T10:00:00", helsinki), activities.get(0).getEnd());

        activities = a.getActivities(
                parse("2015-04-07T00:00:00", helsinki),
                parse("2014-05-06T09:15:00", helsinki),
                parse("2014-06-06T10:15:00", helsinki));
        assertEquals("non-overlapping", 0, activities.size());

    }

    private ZonedDateTime parse(String text, ZoneId stockholm) {
        return LocalDateTime.parse(text).atZone(stockholm);
    }
}