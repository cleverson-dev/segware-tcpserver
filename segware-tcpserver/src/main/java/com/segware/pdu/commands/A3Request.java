package com.segware.pdu.commands;

import com.segware.pdu.ProtocolDataUnit;
import com.segware.pdu.structure.Data;
import com.segware.pdu.structure.Frame;
import com.segware.pdu.structure.data.DateTime;
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
            new com.segware.persistence.entities.A3Request(this, a3PDUResponse).persist();
        } else {
            throw new IllegalStateException("Only a request can be executed, not a response.");
        }
    }
}
