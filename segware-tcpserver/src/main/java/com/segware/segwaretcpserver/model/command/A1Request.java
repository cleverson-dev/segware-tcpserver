package com.segware.segwaretcpserver.model.command;

import com.segware.segwaretcpserver.gateway.database.entities.A1RequestEntity;
import com.segware.segwaretcpserver.model.command.field.Data;
import com.segware.segwaretcpserver.model.command.field.Frame;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class A1Request extends ProtocolDataUnit implements PDURequest {
    private Logger minaLogger = LoggerFactory.getLogger("org.apache.mina");

    public A1Request() {
        super(Frame.TEXT_MESSAGE, new Data(new byte[0]));
    }

    public A1Request(Data data) {
        super(Frame.TEXT_MESSAGE, data);
    }

    @Override
    public void execute(IoSession session) {
        A0Response a0Response = new A0Response();
        session.write(a0Response);
        minaLogger.info("PDU RESPONSE: " + a0Response.toString());
        new A1RequestEntity(this, a0Response).persist();
    }
}
