package com.segware.pdu.commands;

import com.segware.pdu.ProtocolDataUnit;
import com.segware.pdu.structure.Data;
import com.segware.pdu.structure.Frame;
import com.segware.persistence.entities.A1Request;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class A1PDU extends ProtocolDataUnit {
    private Logger minaLogger = LoggerFactory.getLogger("org.apache.mina");

    public A1PDU() {
        super(Frame.TEXT_MESSAGE, new Data(new byte[0]));
    }

    public A1PDU(Data data) {
        super(Frame.TEXT_MESSAGE, data);
    }

    @Override
    public void execute(IoSession session) {
        A0PDU a0PDUResponse = new A0PDU();
        session.write(a0PDUResponse);
        minaLogger.info("PDU RESPONSE: " + a0PDUResponse.toString());
        new A1Request(this, a0PDUResponse).persist();
    }
}
