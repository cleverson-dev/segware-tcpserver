package com.segware.segwaretcpserver.gateway.database.entities;

import com.segware.segwaretcpserver.model.data.DateTime;

import javax.persistence.*;

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
}
