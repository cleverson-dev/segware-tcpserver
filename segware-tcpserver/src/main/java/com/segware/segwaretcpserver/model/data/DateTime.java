package com.segware.segwaretcpserver.model.data;

import com.segware.segwaretcpserver.model.command.field.Data;

import java.time.ZonedDateTime;
import java.util.Objects;

import static com.segware.segwaretcpserver.model.command.CommandUtils.asInt;

public class DateTime {
    public static final int LENGTH = 6;

    private Day day;
    private Month month;
    private Year year;
    private Hour hour;
    private Minute minute;
    private Second second;

    public DateTime(ZonedDateTime zonedDateTime) {
        day = new Day(zonedDateTime.getDayOfMonth());
        month = new Month(zonedDateTime.getMonth().getValue());
        year = new Year(zonedDateTime.getYear());
        hour = new Hour(zonedDateTime.getHour());
        minute = new Minute(zonedDateTime.getMinute());
        second = new Second(zonedDateTime.getSecond());
    }

    private DateTime(Data data) {
        int index = 0;
        byte[] dataByteArray = data.toByteArray();

        day = new Day(asInt(dataByteArray[index++]));
        month = new Month(asInt(dataByteArray[index++]));
        year = new Year(asInt(dataByteArray[index++]));
        hour = new Hour(asInt(dataByteArray[index++]));
        minute = new Minute(asInt(dataByteArray[index++]));
        second = new Second(asInt(dataByteArray[index++]));
    }

    public static DateTime fromData(Data data) {
        return new DateTime(data);
    }

    public byte[] toByteArray() {
        int index = 0;
        byte[] byteArray = new byte[LENGTH];

        byteArray[index++] = day.toByte();
        byteArray[index++] = month.toByte();
        byteArray[index++] = year.toByte();
        byteArray[index++] = hour.toByte();
        byteArray[index++] = minute.toByte();
        byteArray[index++] = second.toByte();

        return byteArray;
    }

    public Day getDay() {
        return day;
    }

    public Month getMonth() {
        return month;
    }

    public Year getYear() {
        return year;
    }

    public Hour getHour() {
        return hour;
    }

    public Minute getMinute() {
        return minute;
    }

    public Second getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateTime dateTime = (DateTime) o;
        return Objects.equals(day, dateTime.day) &&
                Objects.equals(month, dateTime.month) &&
                Objects.equals(year, dateTime.year) &&
                Objects.equals(hour, dateTime.hour) &&
                Objects.equals(minute, dateTime.minute) &&
                Objects.equals(second, dateTime.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year, hour, minute, second);
    }

    @Override
    public String toString() {
        return "DateTime{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                '}';
    }
}
