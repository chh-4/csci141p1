package puzzles.clock;

import puzzles.common.solver.Configuration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * author: CHARLES HENRY HUTSON IV [10 MAR 2024]
 */

public class ClockConfig implements Configuration {
    private final int hours;
    private final int start;
    private final int end;

    public ClockConfig(int hours, int start, int end) {
        this.hours = hours;
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return this.start;
    }
    @Override
    public boolean isSolution() {
        return (start == end);
    }

    @Override
    public Collection<Configuration> getNeighbors() {
        Collection<Configuration> neighbors = new ArrayList<>();
        neighbors.add(new ClockConfig(hours, clockSub(start, hours), end));
        neighbors.add(new ClockConfig(hours, clockAdd(start, hours), end));
        return neighbors;
    }

    public int clockAdd(int start, int hours) {
        int newTime = start + 1;
        if (newTime > hours) {
            newTime = 1;
        }
        return newTime;
    }

    public int clockSub(int start, int hours) {
        int newTime = start - 1;
        if (newTime < 1) {
            newTime = hours;
        }
        return newTime;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ClockConfig clockConfig) {
            return this.hours == clockConfig.hours && this.start == clockConfig.start && this.end == clockConfig.end;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start);
    }

    @Override
    public String toString() {
        return String.valueOf(start);
    }
}
