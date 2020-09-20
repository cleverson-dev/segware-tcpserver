package com.segware;

import com.segware.pdu.commands.PDURequest;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerHandler extends IoHandlerAdapter {
    private Logger minaLogger = LoggerFactory.getLogger("org.apache.mina");

    @Override
    public void messageReceived(IoSession session, Object message) {
        PDURequest pdu = (PDURequest) message;
        minaLogger.info("PDU REQUEST: " + pdu.toString());
        pdu.execute(session);
    }
}
