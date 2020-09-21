package com.segware.segwaretcpserver.gateway.database.entitiy;

import com.segware.segwaretcpserver.model.data.DateTime;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="CURRENT_DATE_TIME")
public class CurrentDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CURRENT_DATE_TIME")
    @SequenceGenerator(name = "SEQ_CURRENT_DATE_TIME", sequenceName = "SEQ_CURRENT_DATE_TIME", allocationSize = 1)
    @Column(name="CURRENT_DATE_TIME_ID")
    private long currentDateTimeId;

    @Column(name="DAY")
    private int day;

    @Column(name="MONTH")
    private int month;

    @Column(name="YEAR")
    private int year;

    @Column(name="HOUR")
    private int hour;

    @Column(name="MINUTE")
    private int minute;

    @Column(name="SECOND")
    private int second;

    @OneToOne(mappedBy = "currentDateTimeEntity")
    private A3ResponseEntity a3ResponseEntity;

    public CurrentDateTimeEntity(DateTime dateTime, A3ResponseEntity a3ResponseEntity) {
        day = dateTime.getDay().asInt();
        month = dateTime.getMonth().asInt();
        year = dateTime.getYear().asInt();
        hour = dateTime.getHour().asInt();
        minute = dateTime.getMinute().asInt();
        second = dateTime.getSecond().asInt();

        this.a3ResponseEntity = a3ResponseEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentDateTimeEntity that = (CurrentDateTimeEntity) o;
        return currentDateTimeId == that.currentDateTimeId &&
                day == that.day &&
                month == that.month &&
                year == that.year &&
                hour == that.hour &&
                minute == that.minute &&
                second == that.second &&
                Objects.equals(a3ResponseEntity, that.a3ResponseEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentDateTimeId, day, month, year, hour, minute, second, a3ResponseEntity);
    }

    @Override
    public String toString() {
        return "CurrentDateTimeEntity{" +
                "currentDateTimeId=" + currentDateTimeId +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                ", a3ResponseEntity=" + a3ResponseEntity +
                '}';
    }
}
