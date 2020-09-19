package com.segware.persistence.entities;

import com.segware.pdu.commands.A0PDU;
import com.segware.pdu.commands.A2PDU;
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
    private A0Response a0Response;

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

    public A2Request(A2PDU a2PDU, A0PDU a0PDU) {
        init = a2PDU.getInit().toByte();
        bytes = a2PDU.getBytes().toByte();
        frame = a2PDU.getFrame().toByte();
        data = a2PDU.getData().toByteArray();
        crc = a2PDU.getCrc().toByte();
        end = a2PDU.getEnd().toByte();

        userInformation = new UserInformation(a2PDU.getUserInformation());
        a0Response = new A0Response(a0PDU);
    }

    public void persist() {
        EntityManager entityManager = DataSource.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(this);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
