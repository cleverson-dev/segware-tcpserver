package com.segware.segwaretcpserver.model.command;

import com.segware.segwaretcpserver.gateway.database.entities.A3RequestEntity;
import com.segware.segwaretcpserver.model.command.field.Data;
import com.segware.segwaretcpserver.model.command.field.Frame;
import com.segware.segwaretcpserver.model.data.DateTime;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class A3Request extends ProtocolDataUnit implements PDURequest {
    private Logger minaLogger = LoggerFactory.getLogger("org.apache.mina");

    private boolean isRequest;

    public A3Request(Data data) {
        super(Frame.CURRENT_DATE_TIME, data);
        isRequest = true;
    }

    @Override
    public void execute(IoSession session) {
        if (isRequest) {
            ZoneId zoneId = ZoneId.of(new String(this.data.toByteArray()));
            ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);

            A3Response a3PDUResponse = new A3Response(new DateTime(zonedDateTime));
            session.write(a3PDUResponse);
            minaLogger.info("PDU RESPONSE: " + a3PDUResponse.toString());
            new A3RequestEntity(this, a3PDUResponse).persist();
        } else {
            throw new IllegalStateException("Only a request can be executed, not a response.");
        }
    }
}
