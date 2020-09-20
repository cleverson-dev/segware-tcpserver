package com.segware.persistence.entities;

import com.segware.pdu.commands.A0Response;
import com.segware.persistence.DataSource;

import javax.persistence.*;

@Entity
@Table(name="A1_REQUEST")
public class A1Request {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_A1_REQUEST")
    @SequenceGenerator(name = "SEQ_A1_REQUEST", sequenceName = "SEQ_A1_REQUEST", allocationSize = 1)
    @Column(name="A1_REQUEST_ID")
    private long a1RequestId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "A0_RESPONSE_ID")
    private com.segware.persistence.entities.A0Response a0Response;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEXT_MESSAGE_ID")
    private TextMessage textMessage;

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

    public A1Request(com.segware.pdu.commands.A1Request a1Request, A0Response a0Response) {
        init = a1Request.getInit().toByte();
        bytes = a1Request.getBytes().toByte();
        frame = a1Request.getFrame().toByte();
        data = a1Request.getData().toByteArray();
        crc = a1Request.getCrc().toByte();
        end = a1Request.getEnd().toByte();

        textMessage = new TextMessage(data, this);
        this.a0Response = new com.segware.persistence.entities.A0Response(a0Response);
    }

    public void persist() {
        EntityManager entityManager = DataSource.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(this);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
