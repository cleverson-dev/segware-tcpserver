package com.segware.segwaretcpserver.gateway.database.entitiy;

import com.segware.segwaretcpserver.model.command.A0Response;
import com.segware.segwaretcpserver.model.command.A2Request;
import com.segware.segwaretcpserver.gateway.database.Database;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name="A2_REQUEST")
public class A2RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_A2_REQUEST")
    @SequenceGenerator(name = "SEQ_A2_REQUEST", sequenceName = "SEQ_A2_REQUEST", allocationSize = 1)
    @Column(name="A2_REQUEST_ID")
    private long a2RequestId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "A0_RESPONSE_ID")
    private A0ResponseEntity a0ResponseEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_INFORMATION_ID")
    private UserInformationEntity userInformationEntity;

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

    public A2RequestEntity(A2Request a2Request, A0Response a0Response) {
        init = a2Request.getInit().toByte();
        bytes = a2Request.getBytes().toByte();
        frame = a2Request.getFrame().toByte();
        data = a2Request.getData().toByteArray();
        crc = a2Request.getCrc().toByte();
        end = a2Request.getEnd().toByte();

        receptionTime = a2Request.getReceptionTime();

        userInformationEntity = new UserInformationEntity(a2Request.getUserInformation(), this);
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
        A2RequestEntity that = (A2RequestEntity) o;
        return a2RequestId == that.a2RequestId &&
                init == that.init &&
                bytes == that.bytes &&
                frame == that.frame &&
                crc == that.crc &&
                end == that.end &&
                Objects.equals(a0ResponseEntity, that.a0ResponseEntity) &&
                Objects.equals(userInformationEntity, that.userInformationEntity) &&
                Arrays.equals(data, that.data) &&
                Objects.equals(receptionTime, that.receptionTime);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(a2RequestId, a0ResponseEntity, userInformationEntity, init, bytes, frame, crc, end, receptionTime);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "A2RequestEntity{" +
                "a2RequestId=" + a2RequestId +
                ", a0ResponseEntity=" + a0ResponseEntity +
                ", userInformationEntity=" + userInformationEntity +
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
