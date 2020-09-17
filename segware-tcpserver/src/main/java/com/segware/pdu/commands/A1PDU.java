package com.segware.pdu.commands;

import com.segware.pdu.ProtocolDataUnit;
import com.segware.pdu.structure.Data;
import com.segware.pdu.structure.Frame;
import org.apache.mina.core.session.IoSession;

public class A1PDU extends ProtocolDataUnit {
    public A1PDU() {
        super(Frame.ACK, new Data(new byte[0]));
    }

    public A1PDU(Data data) {
        super(Frame.ACK, data);
    }

    @Override
    public void execute(IoSession session) {
        session.write(new A0PDU());
    }
}
