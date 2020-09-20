package com.segware.persistence.entities;

import com.segware.persistence.DataSource;

import javax.persistence.*;

@Entity
@Table(name="A3_RESPONSE")
public class A3Response {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_A3_RESPONSE")
    @SequenceGenerator(name = "SEQ_A3_RESPONSE", sequenceName = "SEQ_A3_RESPONSE", allocationSize = 1)
    @Column(name="A3_RESPONSE_ID")
    private long a3ResponseId;

    @OneToOne(mappedBy = "a3Response")
    private A3Request a3Request;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CURRENT_DATE_TIME_ID")
    private CurrentDateTime currentDateTime;

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

    public A3Response(com.segware.pdu.commands.A3Response a3PDUResponse, A3Request a3Request) {
        init = a3PDUResponse.getInit().toByte();
        bytes = a3PDUResponse.getBytes().toByte();
        frame = a3PDUResponse.getFrame().toByte();
        data = a3PDUResponse.getData().toByteArray();
        crc = a3PDUResponse.getCrc().toByte();
        end = a3PDUResponse.getEnd().toByte();

        currentDateTime = new CurrentDateTime(a3PDUResponse.getDateTime(), this);
        this.a3Request = a3Request;
    }

    public void persist() {
        EntityManager entityManager = DataSource.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(this);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
