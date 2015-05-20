package se.cygni.calendar;

import java.util.Optional;
import java.util.function.BinaryOperator;

public class Interval<T extends Comparable<T>> {
    public final BinaryOperator<T> MAX = BinaryOperator.maxBy(Comparable::compareTo);
    public final BinaryOperator<T> MIN = BinaryOperator.minBy(Comparable::compareTo);
    private final T start;
    private final T end;

    /**
     * Creates a new interval of time.
     * @param start start of interval (inclusively)
     * @param end end of interval (exclusively)
     */
    private Interval(T start, T end) {
        this.start = start;
        this.end = end;
    }

    public static <T extends Comparable<T>> Interval<T> of(T startInclusive, T endExclusive) {
        if (startInclusive.compareTo(endExclusive) >= 0) {
            throw new IllegalArgumentException("Start must be before end.");
        }
        return new Interval(startInclusive, endExclusive);
    }

    /**
     * @param other an interval
     * @return true of this interval overlaps with the other interval, false otherwise.
     */
    public boolean overlaps(Interval<T> other) {
        return intersection(other).isPresent();
    }

    /**
     * @param other an interval
     * @return true of this interval is fully contained by the other interval, false otherwise.
     */
    public boolean containedBy(Interval<T> other) {
        return other.encloses(this);
    }

    /**
     * @param other an interval
     * @return true if this interval fully encloses the other interval, false otherwise.
     */
    public boolean encloses(Interval<T> other) {
        return false;
    }

    /**
     * @param other an interval
     * @return the intersection between this and the other interval.
     */
    public Optional<Interval<T>> intersection(Interval<T> other) {
        return null;
    }

    public T getStart() {
        return start;
    }

    public T getEnd() {
        return end;
    }

    public Interval withStart(T start) {
        return Interval.of(start, end);
    }

    public Interval withEnd(T end) {
        return Interval.of(start, end);
    }

    @Override
    public String toString() {
        return "[" + start +  ", " + end + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interval interval = (Interval) o;

        if (start != null ? !start.equals(interval.start) : interval.start != null) return false;
        return !(end != null ? !end.equals(interval.end) : interval.end != null);
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}
