package com.segware.segwaretcpserver.model.data;

import com.segware.segwaretcpserver.model.command.field.Data;

import java.util.Objects;

public class TimeZone {
    private String timeZone;

    public TimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public static TimeZone fromData(Data data) {
        return new TimeZone(new String(data.toByteArray()));
    }

    public byte[] toByteArray() {
        return timeZone.getBytes();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeZone timeZone1 = (TimeZone) o;
        return Objects.equals(timeZone, timeZone1.timeZone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeZone);
    }

    @Override
    public String toString() {
        return "TimeZone{" +
                "timeZone='" + timeZone + '\'' +
                '}';
    }
}
