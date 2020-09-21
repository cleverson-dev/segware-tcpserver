package com.segware.segwaretcpserver.gateway.database.entitiy;

import com.segware.segwaretcpserver.model.command.A3Response;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name="A3_RESPONSE")
public class A3ResponseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_A3_RESPONSE")
    @SequenceGenerator(name = "SEQ_A3_RESPONSE", sequenceName = "SEQ_A3_RESPONSE", allocationSize = 1)
    @Column(name="A3_RESPONSE_ID")
    private long a3ResponseId;

    @OneToOne(mappedBy = "a3ResponseEntity")
    private A3RequestEntity a3RequestEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CURRENT_DATE_TIME_ID")
    private CurrentDateTimeEntity currentDateTimeEntity;

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

    public A3ResponseEntity(A3Response a3Response, A3RequestEntity a3RequestEntity) {
        init = a3Response.getInit().toByte();
        bytes = a3Response.getBytes().toByte();
        frame = a3Response.getFrame().toByte();
        data = a3Response.getData().toByteArray();
        crc = a3Response.getCrc().toByte();
        end = a3Response.getEnd().toByte();

        receptionTime = a3Response.getReceptionTime();

        currentDateTimeEntity = new CurrentDateTimeEntity(a3Response.getDateTime(), this);
        this.a3RequestEntity = a3RequestEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A3ResponseEntity that = (A3ResponseEntity) o;
        return a3ResponseId == that.a3ResponseId &&
                init == that.init &&
                bytes == that.bytes &&
                frame == that.frame &&
                crc == that.crc &&
                end == that.end &&
                Objects.equals(a3RequestEntity, that.a3RequestEntity) &&
                Objects.equals(currentDateTimeEntity, that.currentDateTimeEntity) &&
                Arrays.equals(data, that.data) &&
                Objects.equals(receptionTime, that.receptionTime);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(a3ResponseId, a3RequestEntity, currentDateTimeEntity, init, bytes, frame, crc, end, receptionTime);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "A3ResponseEntity{" +
                "a3ResponseId=" + a3ResponseId +
                ", a3RequestEntity=" + a3RequestEntity +
                ", currentDateTimeEntity=" + currentDateTimeEntity +
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
