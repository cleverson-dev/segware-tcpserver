package com.segware.segwaretcpserver.model.data;

import java.util.Objects;

public class Year {
    private int year;

    public Year(int year) {
        if (isYearValid(year))
            this.year = year % 100;
        else
            throw new IllegalArgumentException("The informed value isn't a valid year value");
    }

    private boolean isYearValid(int year) {
        return ((year >= 1) && (year <= 5000)) ? true : false;
    }

    public byte toByte() {
        return (byte) year;
    }

    public int asInt() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Year year1 = (Year) o;
        return year == year1.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year);
    }

    @Override
    public String toString() {
        return "Year{" +
                "year=" + year +
                '}';
    }
}
