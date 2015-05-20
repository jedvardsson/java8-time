package se.cygni.calendar;

import java.time.ZonedDateTime;

public class BasicActivity implements Activity {
    private final ActivityTemplate activityTemplate;
    private final String title;
    private final ZonedDateTime start;
    private final ZonedDateTime end;

    public BasicActivity(ActivityTemplate activityTemplate, String title, ZonedDateTime start, ZonedDateTime end) {
        this.activityTemplate = activityTemplate;
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public ActivityTemplate getActivityTemplate() {
        return activityTemplate;
    }

    public String getTitle() {
        return title;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return start + " - " + end + " " + title;
    }
}
