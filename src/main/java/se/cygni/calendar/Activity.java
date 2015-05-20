package se.cygni.calendar;

import java.time.ZonedDateTime;

public interface Activity {
    ActivityTemplate getActivityTemplate();
    String getTitle();
    ZonedDateTime getStart();
    ZonedDateTime getEnd();
}
