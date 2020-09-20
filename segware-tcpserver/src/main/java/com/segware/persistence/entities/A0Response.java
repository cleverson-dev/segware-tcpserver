package com.segware.persistence.entities;

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

    public A0Response(com.segware.pdu.commands.A0Response a0Response) {
        init = a0Response.getInit().toByte();
        bytes = a0Response.getBytes().toByte();
        frame = a0Response.getFrame().toByte();
        crc = a0Response.getCrc().toByte();
        end = a0Response.getEnd().toByte();
    }
}
