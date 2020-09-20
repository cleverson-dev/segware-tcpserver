package com.segware.pdu.commands;

import com.segware.pdu.ProtocolDataUnit;
import com.segware.pdu.structure.Data;
import com.segware.pdu.structure.Frame;
import org.apache.mina.core.session.IoSession;

public class A0Response extends ProtocolDataUnit {
    public A0Response() {
        super(Frame.ACK, new Data(new byte[0]));
    }

    public A0Response(Data data) {
        super(Frame.ACK, data);
    }

    @Override
    public void execute(IoSession session) {}
}
