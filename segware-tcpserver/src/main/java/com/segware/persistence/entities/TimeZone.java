package com.segware.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name="TIME_ZONE")
public class TimeZone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIME_ZONE")
    @SequenceGenerator(name = "SEQ_TIME_ZONE", sequenceName = "SEQ_TIME_ZONE", allocationSize = 1)
    @Column(name="TIME_ZONE_ID")
    private long timeZoneId;

    @Column(name="ZONE_NAME")
    private String zoneName;

    @OneToOne(mappedBy = "timeZone")
    private A3Request a3Request;

    public TimeZone(byte[] textMsg) {
        this.zoneName = new String(textMsg);
    }
}
