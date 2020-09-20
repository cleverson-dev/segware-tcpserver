package com.segware.persistence.entities;

import com.segware.persistence.DataSource;

import javax.persistence.*;

@Entity
@Table(name="A3_REQUEST")
public class A3Request {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_A3_REQUEST")
    @SequenceGenerator(name = "SEQ_A3_REQUEST", sequenceName = "SEQ_A3_REQUEST", allocationSize = 1)
    @Column(name="A3_REQUEST_ID")
    private long a3RequestId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "A3_RESPONSE_ID")
    private A3Response a3Response;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TIME_ZONE_ID")
    private TimeZone timeZone;

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

    public A3Request(com.segware.pdu.commands.A3Request a3Request, com.segware.pdu.commands.A3Response a3PDUResponse) {
        init = a3Request.getInit().toByte();
        bytes = a3Request.getBytes().toByte();
        frame = a3Request.getFrame().toByte();
        data = a3Request.getData().toByteArray();
        crc = a3Request.getCrc().toByte();
        end = a3Request.getEnd().toByte();

        timeZone = new TimeZone(a3Request.getData().toByteArray(), this);
        a3Response = new A3Response(a3PDUResponse, this);
    }

    public void persist() {
        EntityManager entityManager = DataSource.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(this);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
