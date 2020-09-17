package com.segware;

import com.segware.pdu.commands.A0PDU;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ServerHandler extends IoHandlerAdapter {

    @Override
    public void messageReceived(IoSession session, Object message) {
        session.write(new A0PDU());
    }
}
