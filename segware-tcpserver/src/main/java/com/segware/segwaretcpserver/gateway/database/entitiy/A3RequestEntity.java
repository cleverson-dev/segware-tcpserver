package com.segware.segwaretcpserver.gateway.database.entitiy;

import com.segware.segwaretcpserver.model.command.A3Request;
import com.segware.segwaretcpserver.model.command.A3Response;
import com.segware.segwaretcpserver.gateway.database.Database;

import javax.persistence.*;

@Entity
@Table(name="A3_REQUEST")
public class A3RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_A3_REQUEST")
    @SequenceGenerator(name = "SEQ_A3_REQUEST", sequenceName = "SEQ_A3_REQUEST", allocationSize = 1)
    @Column(name="A3_REQUEST_ID")
    private long a3RequestId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "A3_RESPONSE_ID")
    private A3ResponseEntity a3ResponseEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TIME_ZONE_ID")
    private TimeZoneEntity timeZoneEntity;

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

    public A3RequestEntity(A3Request a3Request, A3Response a3Response) {
        init = a3Request.getInit().toByte();
        bytes = a3Request.getBytes().toByte();
        frame = a3Request.getFrame().toByte();
        data = a3Request.getData().toByteArray();
        crc = a3Request.getCrc().toByte();
        end = a3Request.getEnd().toByte();

        timeZoneEntity = new TimeZoneEntity(a3Request.getTimeZone(), this);
        a3ResponseEntity = new A3ResponseEntity(a3Response, this);
    }

    public void persist() {
        EntityManager entityManager = Database.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(this);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
