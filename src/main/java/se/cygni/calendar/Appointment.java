package se.cygni.calendar;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

public class Appointment extends BasicActivityTemplate {
    private ZonedDateTime start;
    private ZonedDateTime end;

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public List<Activity> getActivities(ZonedDateTime now, ZonedDateTime calStart, ZonedDateTime calEnd) {
        return null;
    }

    @Override
    public String toString() {
        return getTitle() + " " + start + "-" + end;
    }
}
