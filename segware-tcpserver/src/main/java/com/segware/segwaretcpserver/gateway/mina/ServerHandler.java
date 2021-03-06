package com.segware.segwaretcpserver.gateway.mina;

import com.segware.segwaretcpserver.model.command.CommandRequest;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerHandler extends IoHandlerAdapter {
    private Logger minaLogger = LoggerFactory.getLogger("org.apache.mina");

    @Override
    public void messageReceived(IoSession session, Object message) {
        CommandRequest pdu = (CommandRequest) message;
        minaLogger.info("PDU REQUEST: " + pdu.toString());
        pdu.execute(session);
    }
}
