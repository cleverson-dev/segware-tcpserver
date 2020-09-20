package com.segware.persistence.entities;

import com.segware.pdu.commands.A3PDU;
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

    public A3Request(A3PDU a3PDURequest, A3PDU a3PDUResponse) {
        init = a3PDURequest.getInit().toByte();
        bytes = a3PDURequest.getBytes().toByte();
        frame = a3PDURequest.getFrame().toByte();
        data = a3PDURequest.getData().toByteArray();
        crc = a3PDURequest.getCrc().toByte();
        end = a3PDURequest.getEnd().toByte();

        timeZone = new TimeZone(a3PDURequest.getData().toByteArray());
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
