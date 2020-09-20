package com.segware.segwaretcpserver.gateway.database.entities;

import com.segware.segwaretcpserver.model.command.A3Response;

import javax.persistence.*;

@Entity
@Table(name="A3_RESPONSE")
public class A3ResponseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_A3_RESPONSE")
    @SequenceGenerator(name = "SEQ_A3_RESPONSE", sequenceName = "SEQ_A3_RESPONSE", allocationSize = 1)
    @Column(name="A3_RESPONSE_ID")
    private long a3ResponseId;

    @OneToOne(mappedBy = "a3ResponseEntity")
    private A3RequestEntity a3RequestEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CURRENT_DATE_TIME_ID")
    private CurrentDateTimeEntity currentDateTimeEntity;

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

    public A3ResponseEntity(A3Response a3Response, A3RequestEntity a3RequestEntity) {
        init = a3Response.getInit().toByte();
        bytes = a3Response.getBytes().toByte();
        frame = a3Response.getFrame().toByte();
        data = a3Response.getData().toByteArray();
        crc = a3Response.getCrc().toByte();
        end = a3Response.getEnd().toByte();

        currentDateTimeEntity = new CurrentDateTimeEntity(a3Response.getDateTime(), this);
        this.a3RequestEntity = a3RequestEntity;
    }
}
