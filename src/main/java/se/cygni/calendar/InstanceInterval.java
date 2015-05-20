package se.cygni.calendar;

import java.time.Instant;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class InstanceInterval {
    public static final BinaryOperator<Instant> MAX_INSTANT = BinaryOperator.maxBy(Instant::compareTo);
    public static final BinaryOperator<Instant> MIN_INSTANT = BinaryOperator.minBy(Instant::compareTo);
    private final Instant start;
    private final Instant end;

    /**
     * Creates a new interval of time.
     * @param start start of interval (inclusively)
     * @param end end of interval (exclusively)
     */
    private InstanceInterval(Instant start, Instant end) {
        this.start = start;
        this.end = end;
    }

    public static InstanceInterval of(Instant startInclusive, Instant endExclusive) {
        if (!startInclusive.isBefore(endExclusive)) {
            throw new IllegalArgumentException("Start must be before end.");
        }
        return new InstanceInterval(startInclusive, endExclusive);
    }

    /**
     * @param other an interval
     * @return true of this interval overlaps with the other interval, false otherwise.
     */
    public boolean overlaps(InstanceInterval other) {
        return intersection(other).isPresent();
    }

    /**
     * @param other an interval
     * @return true of this interval is fully contained by the other interval, false otherwise.
     */
    public boolean containedBy(InstanceInterval other) {
        return !other.getStart().isAfter(this.getStart()) && !other.getEnd().isBefore(this.getEnd());
    }

    /**
     * @param other an interval
     * @return true if this interval fully encloses the other interval, false otherwise.
     */
    public boolean encloses(InstanceInterval other) {
        return other.containedBy(this);
    }

    /**
     * @param other an interval
     * @return the intersection between this and the other interval.
     */
    public Optional<InstanceInterval> intersection(InstanceInterval other) {
        Instant start = MAX_INSTANT.apply(getStart(), other.getStart());
        Instant end = MIN_INSTANT.apply(getEnd(), other.getEnd());

        if (start.isBefore(end)) {
            return Optional.of(InstanceInterval.of(start, end));
        }
        return Optional.empty();
    }

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }

    public InstanceInterval withStart(Instant start) {
        return InstanceInterval.of(start, end);
    }

    public InstanceInterval withEnd(Instant end) {
        return InstanceInterval.of(start, end);
    }

    @Override
    public String toString() {
        return "[" + start +  ", " + end + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstanceInterval instanceInterval = (InstanceInterval) o;

        if (start != null ? !start.equals(instanceInterval.start) : instanceInterval.start != null) return false;
        return !(end != null ? !end.equals(instanceInterval.end) : instanceInterval.end != null);
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}
