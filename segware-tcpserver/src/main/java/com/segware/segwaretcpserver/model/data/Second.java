package com.segware.segwaretcpserver.model.data;

import java.util.Objects;

public class Second {
    private int second;

    public Second(int second) {
        if (isSecondValid(second))
            this.second = second;
        else
            throw new IllegalArgumentException("The informed value isn't a valid second value");
    }

    private boolean isSecondValid(int second) {
        return ((second >= 0) && (second <= 59)) ? true : false;
    }

    public byte toByte() {
        return (byte) second;
    }

    public int asInt() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Second second1 = (Second) o;
        return second == second1.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(second);
    }

    @Override
    public String toString() {
        return "Second{" +
                "second=" + second +
                '}';
    }
}
