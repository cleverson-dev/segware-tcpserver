package com.segware.segwaretcpserver.gateway.database.entitiy;

import com.segware.segwaretcpserver.model.command.A0Response;
import com.segware.segwaretcpserver.model.command.A1Request;
import com.segware.segwaretcpserver.gateway.database.Database;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name="A1_REQUEST")
public class A1RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_A1_REQUEST")
    @SequenceGenerator(name = "SEQ_A1_REQUEST", sequenceName = "SEQ_A1_REQUEST", allocationSize = 1)
    @Column(name="A1_REQUEST_ID")
    private long a1RequestId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "A0_RESPONSE_ID")
    private A0ResponseEntity a0ResponseEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEXT_MESSAGE_ID")
    private TextMessageEntity textMessageEntity;

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

    public A1RequestEntity(A1Request a1Request, A0Response a0Response) {
        init = a1Request.getInit().toByte();
        bytes = a1Request.getBytes().toByte();
        frame = a1Request.getFrame().toByte();
        data = a1Request.getData().toByteArray();
        crc = a1Request.getCrc().toByte();
        end = a1Request.getEnd().toByte();

        receptionTime = a1Request.getReceptionTime();

        textMessageEntity = new TextMessageEntity(a1Request.getTextMessage(), this);
        this.a0ResponseEntity = new A0ResponseEntity(a0Response);
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
        A1RequestEntity that = (A1RequestEntity) o;
        return a1RequestId == that.a1RequestId &&
                init == that.init &&
                bytes == that.bytes &&
                frame == that.frame &&
                crc == that.crc &&
                end == that.end &&
                Objects.equals(a0ResponseEntity, that.a0ResponseEntity) &&
                Objects.equals(textMessageEntity, that.textMessageEntity) &&
                Arrays.equals(data, that.data) &&
                Objects.equals(receptionTime, that.receptionTime);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(a1RequestId, a0ResponseEntity, textMessageEntity, init, bytes, frame, crc, end, receptionTime);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "A1RequestEntity{" +
                "a1RequestId=" + a1RequestId +
                ", a0ResponseEntity=" + a0ResponseEntity +
                ", textMessageEntity=" + textMessageEntity +
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
