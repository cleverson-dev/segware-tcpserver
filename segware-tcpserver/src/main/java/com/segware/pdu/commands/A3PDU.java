package com.segware.pdu.commands;

import com.segware.pdu.ProtocolDataUnit;
import com.segware.pdu.structure.Data;
import com.segware.pdu.structure.Frame;
import com.segware.pdu.structure.data.DateTime;
import com.segware.persistence.entities.A3Request;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class A3PDU extends ProtocolDataUnit {
    private Logger minaLogger = LoggerFactory.getLogger("org.apache.mina");

    private boolean isRequest;
    private DateTime dateTime;

    private A3PDU(Data data) {
        super(Frame.CURRENT_DATE_TIME, data);
        isRequest = true;
    }

    private A3PDU(Data data, DateTime dateTime) {
        super(Frame.CURRENT_DATE_TIME, data);
        this.dateTime = dateTime;
    }

    public static A3PDU getRequestInstance(Data data) {
        return new A3PDU(data);
    }

    public static A3PDU getResponseInstance(Data data) {
        return new A3PDU(data, DateTime.fromData(data));
    }

    public static A3PDU getResponseInstance(DateTime dateTime) {
        return new A3PDU(new Data(dateTime.toByteArray()), dateTime);
    }

    @Override
    public void execute(IoSession session) {
        if (isRequest) {
            ZoneId zoneId = ZoneId.of(new String(this.data.toByteArray()));
            ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);

            A3PDU a3PDUResponse = A3PDU.getResponseInstance(new DateTime(zonedDateTime));
            session.write(a3PDUResponse);
            minaLogger.info("PDU RESPONSE: " + a3PDUResponse.toString());
            new A3Request(this, a3PDUResponse).persist();
        } else {
            throw new IllegalStateException("Only a request can be executed, not a response.");
        }
    }

    public DateTime getDateTime() {
        return dateTime;
    }
}
