package com.segware.segwaretcpserver.gateway.database.entitiy;

import com.segware.segwaretcpserver.model.data.TimeZone;

import javax.persistence.*;
import java.util.Objects;

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

    public TimeZoneEntity(TimeZone timeZone, A3RequestEntity a3RequestEntity) {
        this.zoneName = timeZone.getTimeZone();
        this.a3RequestEntity = a3RequestEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeZoneEntity that = (TimeZoneEntity) o;
        return timeZoneId == that.timeZoneId &&
                Objects.equals(zoneName, that.zoneName) &&
                Objects.equals(a3RequestEntity, that.a3RequestEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeZoneId, zoneName, a3RequestEntity);
    }

    @Override
    public String toString() {
        return "TimeZoneEntity{" +
                "timeZoneId=" + timeZoneId +
                ", zoneName='" + zoneName + '\'' +
                ", a3RequestEntity=" + a3RequestEntity +
                '}';
    }
}
