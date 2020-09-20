package com.segware.segwaretcpserver.segwaretcpserver.model.data;

import java.util.Objects;

public class Month {
    private int month;

    public Month(int month) {
        if (isMonthValid(month))
            this.month = month;
        else
            throw new IllegalArgumentException("The informed value isn't a valid month value");
    }

    private boolean isMonthValid(int month) {
        return ((month >= 1) && (month <= 31)) ? true : false;
    }

    public byte toByte() {
        return (byte) month;
    }

    public int asInt() {
        return month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Month month1 = (Month) o;
        return month == month1.month;
    }

    @Override
    public int hashCode() {
        return Objects.hash(month);
    }

    @Override
    public String toString() {
        return "Month{" +
                "month=" + month +
                '}';
    }
}
