package se.cygni.calendar;

import java.time.ZonedDateTime;
import java.util.List;

/**
 *
 */
public interface ActivityTemplate {

    /**
     * @return the title of the calendar item.
     */
    String getTitle();

    /**
     * This method is called when displaying activities in the calendar that overlap with the range of dates.
     * <p>
     * Returns a list of scheduled activities that this template results in.
     *
     * @param now   the current time (and time zone) of the observer of the calender.
     * @param start the start time of the activity
     * @param end   the end time (exclusively) of the activity
     * @return a list of zero or more activities that comes as a result of this template.
     */
    List<Activity> getActivities(ZonedDateTime now, ZonedDateTime start, ZonedDateTime end);
}
