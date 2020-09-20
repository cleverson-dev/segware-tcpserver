package com.segware.persistence.entities;

import com.segware.pdu.commands.A0Response;
import com.segware.persistence.DataSource;

import javax.persistence.*;

@Entity
@Table(name="A2_REQUEST")
public class A2Request {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_A2_REQUEST")
    @SequenceGenerator(name = "SEQ_A2_REQUEST", sequenceName = "SEQ_A2_REQUEST", allocationSize = 1)
    @Column(name="A2_REQUEST_ID")
    private long a2RequestId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "A0_RESPONSE_ID")
    private com.segware.persistence.entities.A0Response a0Response;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_INFORMATION_ID")
    private UserInformation userInformation;

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

    public A2Request(com.segware.pdu.commands.A2Request a2Request, A0Response a0Response) {
        init = a2Request.getInit().toByte();
        bytes = a2Request.getBytes().toByte();
        frame = a2Request.getFrame().toByte();
        data = a2Request.getData().toByteArray();
        crc = a2Request.getCrc().toByte();
        end = a2Request.getEnd().toByte();

        userInformation = new UserInformation(a2Request.getUserInformation(), this);
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
