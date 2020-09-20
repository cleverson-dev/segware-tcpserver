package com.segware.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name="TIME_ZONE")
public class TimeZone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_INFORMATION")
    @SequenceGenerator(name = "SEQ_USER_INFORMATION", sequenceName = "SEQ_USER_INFORMATION", allocationSize = 1)
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
