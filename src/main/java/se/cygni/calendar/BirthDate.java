package se.cygni.calendar;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

public class BirthDate implements ActivityTemplate {
    private String title;
    private LocalDate localDate;

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Activity> getActivities(ZonedDateTime now, ZonedDateTime start, ZonedDateTime end) {
        return null;
    }
}
