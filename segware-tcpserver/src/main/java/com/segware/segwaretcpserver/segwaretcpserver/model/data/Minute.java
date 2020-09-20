package com.segware.segwaretcpserver.segwaretcpserver.model.data;

import java.util.Objects;

public class Minute {
    private int minute;

    public Minute(int minute) {
        if (isMinuteValid(minute))
            this.minute = minute;
        else
            throw new IllegalArgumentException("The informed value isn't a valid minute value");
    }

    private boolean isMinuteValid(int minute) {
        return ((minute >= 0) && (minute <= 59)) ? true : false;
    }

    public byte toByte() {
        return (byte) minute;
    }

    public int asInt() {
        return minute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Minute minute1 = (Minute) o;
        return minute == minute1.minute;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minute);
    }

    @Override
    public String toString() {
        return "Minute{" +
                "minute=" + minute +
                '}';
    }
}
