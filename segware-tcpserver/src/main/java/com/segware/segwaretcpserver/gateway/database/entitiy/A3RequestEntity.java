package com.segware.segwaretcpserver.gateway.database.entitiy;

import com.segware.segwaretcpserver.model.command.A3Request;
import com.segware.segwaretcpserver.model.command.A3Response;
import com.segware.segwaretcpserver.gateway.database.Database;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name="A3_REQUEST")
public class A3RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_A3_REQUEST")
    @SequenceGenerator(name = "SEQ_A3_REQUEST", sequenceName = "SEQ_A3_REQUEST", allocationSize = 1)
    @Column(name="A3_REQUEST_ID")
    private long a3RequestId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "A3_RESPONSE_ID")
    private A3ResponseEntity a3ResponseEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TIME_ZONE_ID")
    private TimeZoneEntity timeZoneEntity;

    @Column(name="INIT_FIELD")
    private byte init;

    @Column(name="BYTES_FIELD")
    private byte bytes;

    @Column(name="FRAME_FIELD")
    private byte frame;

    @Column(name="DATA_FIELD")
    private byte[] data;

    @Column(name="CRC_FIELD")
    private byte crc;

    @Column(name="END_FIELD")
    private byte end;

    @Column(name="RECEPTION_TIME")
    private LocalDateTime receptionTime;

    public A3RequestEntity(A3Request a3Request, A3Response a3Response) {
        init = a3Request.getInit().toByte();
        bytes = a3Request.getBytes().toByte();
        frame = a3Request.getFrame().toByte();
        data = a3Request.getData().toByteArray();
        crc = a3Request.getCrc().toByte();
        end = a3Request.getEnd().toByte();

        receptionTime = a3Request.getReceptionTime();

        timeZoneEntity = new TimeZoneEntity(a3Request.getTimeZone(), this);
        a3ResponseEntity = new A3ResponseEntity(a3Response, this);
    }

    public void persist() {
        EntityManager entityManager = Database.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(this);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A3RequestEntity that = (A3RequestEntity) o;
        return a3RequestId == that.a3RequestId &&
                init == that.init &&
                bytes == that.bytes &&
                frame == that.frame &&
                crc == that.crc &&
                end == that.end &&
                Objects.equals(a3ResponseEntity, that.a3ResponseEntity) &&
                Objects.equals(timeZoneEntity, that.timeZoneEntity) &&
                Arrays.equals(data, that.data) &&
                Objects.equals(receptionTime, that.receptionTime);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(a3RequestId, a3ResponseEntity, timeZoneEntity, init, bytes, frame, crc, end, receptionTime);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "A3RequestEntity{" +
                "a3RequestId=" + a3RequestId +
                ", a3ResponseEntity=" + a3ResponseEntity +
                ", timeZoneEntity=" + timeZoneEntity +
                ", init=" + init +
                ", bytes=" + bytes +
                ", frame=" + frame +
                ", data=" + Arrays.toString(data) +
                ", crc=" + crc +
                ", end=" + end +
                ", receptionTime=" + receptionTime +
                '}';
    }
}
