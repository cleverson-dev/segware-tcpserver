package com.segware.persistence.entities;

import com.segware.pdu.commands.A0PDU;

import javax.persistence.*;

@Entity
@Table(name="A0_RESPONSE")
public class A0Response {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_A0_RESPONSE")
    @SequenceGenerator(name = "SEQ_A0_RESPONSE", sequenceName = "SEQ_A0_RESPONSE", allocationSize = 1)
    @Column(name="A0_RESPONSE_ID")
    private long a0ResponseId;

    @Column(name="INIT_FIELD")
    private byte init;

    @Column(name="BYTES_FIELD")
    private byte bytes;

    @Column(name="FRAME_FIELD")
    private byte frame;

    @Column(name="CRC_FIELD")
    private byte crc;

    @Column(name="END_FIELD")
    private byte end;

    @OneToOne(mappedBy = "textMessage")
    private A1Request a1Request;

    public A0Response(A0PDU a0PDU) {
        init = a0PDU.getInit().toByte();
        bytes = a0PDU.getBytes().toByte();
        frame = a0PDU.getFrame().toByte();
        crc = a0PDU.getCrc().toByte();
        end = a0PDU.getEnd().toByte();
    }
}
