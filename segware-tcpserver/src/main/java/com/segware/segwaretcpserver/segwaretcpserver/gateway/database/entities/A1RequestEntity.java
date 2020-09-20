package com.segware.segwaretcpserver.segwaretcpserver.gateway.database.entities;

import com.segware.segwaretcpserver.segwaretcpserver.model.command.A0Response;
import com.segware.segwaretcpserver.segwaretcpserver.model.command.A1Request;
import com.segware.segwaretcpserver.segwaretcpserver.gateway.database.DataSource;

import javax.persistence.*;

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

    public A1RequestEntity(A1Request a1Request, A0Response a0Response) {
        init = a1Request.getInit().toByte();
        bytes = a1Request.getBytes().toByte();
        frame = a1Request.getFrame().toByte();
        data = a1Request.getData().toByteArray();
        crc = a1Request.getCrc().toByte();
        end = a1Request.getEnd().toByte();

        textMessageEntity = new TextMessageEntity(data, this);
        this.a0ResponseEntity = new A0ResponseEntity(a0Response);
    }

    public void persist() {
        EntityManager entityManager = DataSource.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(this);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
