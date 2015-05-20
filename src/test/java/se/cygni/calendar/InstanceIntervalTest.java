package se.cygni.calendar;

import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InstanceIntervalTest {

    private InstanceInterval left;
    private InstanceInterval middle;
    private InstanceInterval right;
    private InstanceInterval outside;
    private InstanceInterval contained;
    private InstanceInterval encloses;
    private InstanceInterval intersection;

    @Before
    public void before() throws Exception {
        left = InstanceInterval.of(
                Instant.parse("2015-01-01T00:00:00Z"),
                Instant.parse("2015-01-10T00:00:00Z")
        );

        middle = InstanceInterval.of(
                Instant.parse("2015-01-08T00:00:00Z"),
                Instant.parse("2015-01-18T00:00:00Z")
        );

        right = InstanceInterval.of(
                Instant.parse("2015-01-15T00:00:00Z"),
                Instant.parse("2015-01-25T00:00:00Z")
        );

        outside = InstanceInterval.of(
                Instant.parse("2014-01-15T00:00:00Z"),
                Instant.parse("2014-01-25T00:00:00Z")
        );

        contained = InstanceInterval.of(
                Instant.parse("2015-01-10T00:00:00Z"),
                Instant.parse("2015-01-16T00:00:00Z")
        );

        encloses = InstanceInterval.of(
                Instant.parse("2015-01-01T00:00:00Z"),
                Instant.parse("2015-01-31T00:00:00Z")
        );

        intersection = InstanceInterval.of(
                Instant.parse("2015-01-08T00:00:00Z"),
                Instant.parse("2015-01-10T00:00:00Z")
        );
    }

    @Test
    public void testOverlaps() throws Exception {
        assertTrue(left.overlaps(middle));
        assertTrue(right.overlaps(middle));
        assertTrue(contained.overlaps(middle));
        assertTrue(encloses.overlaps(middle));
    }


    @Test
    public void testContained() throws Exception {
        assertTrue(contained.containedBy(middle));
        assertFalse(middle.containedBy(contained));
    }

    @Test
    public void testEncloses() throws Exception {
        assertTrue(encloses.encloses(middle));
        assertFalse(middle.encloses(encloses));
    }

    @Test
    public void testIntersection() throws Exception {
        assertEquals(intersection, left.intersection(middle).get());
        assertFalse(left.intersection(outside).isPresent());
    }
}