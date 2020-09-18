package com.segware.pdu.structure.data;

import java.util.Objects;

public class Hour {
    private int hour;

    public Hour(int hour) {
        if (isHourValid(hour))
            this.hour = hour;
        else
            throw new IllegalArgumentException("The informed value isn't a valid hour value");
    }

    private boolean isHourValid(int hour) {
        return ((hour >= 0) && (hour <= 23)) ? true : false;
    }

    public byte toByte() {
        return (byte) hour;
    }

    public int asInt() {
        return hour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hour hour1 = (Hour) o;
        return hour == hour1.hour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour);
    }

    @Override
    public String toString() {
        return "Hour{" +
                "hour=" + hour +
                '}';
    }
}
