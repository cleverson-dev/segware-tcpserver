package com.segware.segwaretcpserver.gateway.database.entitiy;

import javax.persistence.*;

@Entity
@Table(name="TIME_ZONE")
public class TimeZoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIME_ZONE")
    @SequenceGenerator(name = "SEQ_TIME_ZONE", sequenceName = "SEQ_TIME_ZONE", allocationSize = 1)
    @Column(name="TIME_ZONE_ID")
    private long timeZoneId;

    @Column(name="ZONE_NAME")
    private String zoneName;

    @OneToOne(mappedBy = "timeZoneEntity")
    private A3RequestEntity a3RequestEntity;

    public TimeZoneEntity(byte[] textMsg, A3RequestEntity a3RequestEntity) {
        this.zoneName = new String(textMsg);
        this.a3RequestEntity = a3RequestEntity;
    }
}
