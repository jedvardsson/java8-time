package se.cygni.calendar;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.ArrayList;
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
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(now.getZone());
        Interval<? super ChronoZonedDateTime<?>> interval = Interval.of(start, end);

        List<Activity> activities = new ArrayList<>();

        while (interval.contains(zonedDateTime)) {
            String title = this.title + " " + Period.between(localDate, zonedDateTime.toLocalDate()).getYears() + " years";
            activities.add(new BasicActivity(this, title, zonedDateTime, zonedDateTime.plusDays(1)));
            zonedDateTime.plusYears(1);
        }
        return activities;
    }
}
