package com.segware.segwaretcpserver.model.command;

import com.segware.segwaretcpserver.model.command.field.Data;
import com.segware.segwaretcpserver.model.command.field.Frame;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class A1Request extends Command implements CommandRequest {
    private Logger minaLogger = LoggerFactory.getLogger("org.apache.mina");

    private CommandRepository commandRepository;

    public A1Request(Data data, CommandRepository commandRepository) {
        super(Frame.TEXT_MESSAGE, data);
        this.commandRepository = commandRepository;
    }

    @Override
    public void execute(IoSession session) {
        A0Response a0Response = new A0Response();
        session.write(a0Response);
        minaLogger.info("PDU RESPONSE: " + a0Response.toString());
        commandRepository.persist(this, a0Response);
    }
}
