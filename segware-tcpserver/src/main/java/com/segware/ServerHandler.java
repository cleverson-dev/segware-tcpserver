package com.segware;

import com.segware.pdu.ProtocolDataUnit;
import com.segware.pdu.commands.A0PDU;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ServerHandler extends IoHandlerAdapter {

    @Override
    public void messageReceived(IoSession session, Object message) {
        ProtocolDataUnit pdu = (ProtocolDataUnit) message;
        pdu.execute(session);
    }
}
