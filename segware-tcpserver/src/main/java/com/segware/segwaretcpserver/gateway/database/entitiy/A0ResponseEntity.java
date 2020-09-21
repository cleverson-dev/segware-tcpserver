package com.segware.segwaretcpserver.gateway.database.entitiy;

import com.segware.segwaretcpserver.model.command.A0Response;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="A0_RESPONSE")
public class A0ResponseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_A0_RESPONSE")
    @SequenceGenerator(name = "SEQ_A0_RESPONSE", sequenceName = "SEQ_A0_RESPONSE", allocationSize = 1)
    @Column(name="A0_RESPONSE_ID")
    private long a0ResponseId;

    @Column(name="INIT_FIELD")
    private byte init;

    @Column(name="BYTES_FIELD")
    private byte bytes;

    @Column(name="FRAME_FIELD")
    private byte frame;

    @Column(name="CRC_FIELD")
    private byte crc;

    @Column(name="END_FIELD")
    private byte end;

    @Column(name="RECEPTION_TIME")
    private LocalDateTime receptionTime;

    public A0ResponseEntity(A0Response a0Response) {
        init = a0Response.getInit().toByte();
        bytes = a0Response.getBytes().toByte();
        frame = a0Response.getFrame().toByte();
        crc = a0Response.getCrc().toByte();
        end = a0Response.getEnd().toByte();

        receptionTime = a0Response.getReceptionTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A0ResponseEntity that = (A0ResponseEntity) o;
        return a0ResponseId == that.a0ResponseId &&
                init == that.init &&
                bytes == that.bytes &&
                frame == that.frame &&
                crc == that.crc &&
                end == that.end &&
                Objects.equals(receptionTime, that.receptionTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a0ResponseId, init, bytes, frame, crc, end, receptionTime);
    }

    @Override
    public String toString() {
        return "A0ResponseEntity{" +
                "a0ResponseId=" + a0ResponseId +
                ", init=" + init +
                ", bytes=" + bytes +
                ", frame=" + frame +
                ", crc=" + crc +
                ", end=" + end +
                ", receptionTime=" + receptionTime +
                '}';
    }
}
