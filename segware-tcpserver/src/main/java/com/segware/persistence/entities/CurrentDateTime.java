package com.segware.persistence.entities;

import com.segware.pdu.structure.data.DateTime;

import javax.persistence.*;

@Entity
@Table(name="CURRENT_DATE_TIME")
public class CurrentDateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_INFORMATION")
    @SequenceGenerator(name = "SEQ_USER_INFORMATION", sequenceName = "SEQ_USER_INFORMATION", allocationSize = 1)
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

    @OneToOne(mappedBy = "currentDateTime")
    private A3Response a3Response;

    public CurrentDateTime(DateTime dateTime) {
        day = dateTime.getDay().asInt();
        month = dateTime.getMonth().asInt();
        year = dateTime.getYear().asInt();
        hour = dateTime.getHour().asInt();
        minute = dateTime.getMinute().asInt();
        second = dateTime.getSecond().asInt();
    }
}
