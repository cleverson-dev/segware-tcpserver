package com.segware.segwaretcpserver.model.command;

import com.segware.segwaretcpserver.model.command.field.Data;
import com.segware.segwaretcpserver.model.command.field.Frame;
import com.segware.segwaretcpserver.model.data.DateTime;
import com.segware.segwaretcpserver.model.data.TimeZone;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class A3Request extends Command implements CommandRequest {
    private Logger minaLogger = LoggerFactory.getLogger("org.apache.mina");

    private CommandRepository commandRepository;
    private TimeZone timeZone;

    public A3Request(TimeZone timeZone, CommandRepository commandRepository) {
        super(Frame.CURRENT_DATE_TIME, new Data(timeZone.toByteArray()));
        this.commandRepository = commandRepository;
    }

    public A3Request(Data data, CommandRepository commandRepository) {
        super(Frame.CURRENT_DATE_TIME, data);
        this.commandRepository = commandRepository;
        this.timeZone = TimeZone.fromData(data);
    }

    @Override
    public void execute(IoSession session) {
        ZoneId zoneId = ZoneId.of(new String(this.data.toByteArray()));
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);

        A3Response a3Response = new A3Response(new DateTime(zonedDateTime));
        session.write(a3Response);
        minaLogger.info("PDU RESPONSE: " + a3Response.toString());
        commandRepository.persist(this, a3Response);
    }
}
