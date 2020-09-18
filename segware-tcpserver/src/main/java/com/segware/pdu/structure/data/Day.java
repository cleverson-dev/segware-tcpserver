package com.segware.pdu.structure.data;

import java.util.Objects;

public class Day {
    private int day;

    public Day(int day) {
        if (isDayValid(day))
            this.day = day;
        else
            throw new IllegalArgumentException("The informed value isn't a valid day value");
    }

    private boolean isDayValid(int day) {
        return ((day >= 1) && (day <= 31)) ? true : false;
    }

    public byte toByte() {
        return (byte) day;
    }

    public int asInt() {
        return day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day1 = (Day) o;
        return day == day1.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day);
    }

    @Override
    public String toString() {
        return "Day{" +
                "day=" + day +
                '}';
    }
}
