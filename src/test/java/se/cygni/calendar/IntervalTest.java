package se.cygni.calendar;

import org.junit.Test;

import static org.junit.Assert.*;

public class IntervalTest {

    @Test
    public void testOverlaps() throws Exception {
        assertTrue(Interval.of(5, 10).overlaps(Interval.of(1, 12)));
        assertTrue(Interval.of(5, 10).overlaps(Interval.of(2, 7)));
        assertTrue(Interval.of(5, 10).overlaps(Interval.of(8, 12)));
        assertTrue(Interval.of(5, 10).overlaps(Interval.of(6, 8)));
        assertFalse(Interval.of(5, 10).overlaps(Interval.of(1, 4)));
        assertFalse(Interval.of(5, 10).overlaps(Interval.of(11, 20)));
    }

    @Test
    public void testContainedBy() throws Exception {
        assertTrue(Interval.of(5, 10).containedBy(Interval.of(1, 12)));
        assertFalse(Interval.of(1, 12).containedBy(Interval.of(5, 10)));
        assertFalse(Interval.of(5, 10).containedBy(Interval.of(2, 7)));
    }

    @Test
    public void testContains() throws Exception {
        assertTrue(Interval.of(5, 10).contains(6));
        assertTrue(Interval.of(5, 10).contains(10));
        assertFalse(Interval.of(5, 10).contains(11));
        assertFalse(Interval.of(5, 10).contains(4));
    }

    @Test
    public void testEncloses() throws Exception {
        assertFalse(Interval.of(5, 10).encloses(Interval.of(1, 12)));
        assertTrue(Interval.of(1, 12).encloses(Interval.of(5, 10)));
        assertFalse(Interval.of(5, 10).encloses(Interval.of(2, 7)));
    }

    @Test
    public void testIntersection() throws Exception {
        assertEquals(Interval.of(5, 10), Interval.of(5, 10).intersection(Interval.of(1, 12)).get());
        assertEquals(Interval.of(5, 7), Interval.of(5, 10).intersection(Interval.of(2, 7)).get());
        assertEquals(Interval.of(8, 10), Interval.of(5, 10).intersection(Interval.of(8, 12)).get());
        assertEquals(Interval.of(6, 8), Interval.of(5, 10).intersection(Interval.of(6, 8)).get());
        assertFalse(Interval.of(5, 10).intersection(Interval.of(1, 4)).isPresent());
        assertFalse(Interval.of(5, 10).intersection(Interval.of(11, 20)).isPresent());
    }
}