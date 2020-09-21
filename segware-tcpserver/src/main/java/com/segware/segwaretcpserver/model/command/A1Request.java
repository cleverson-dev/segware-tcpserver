package com.segware.segwaretcpserver.model.command;

import com.segware.segwaretcpserver.model.command.field.Data;
import com.segware.segwaretcpserver.model.command.field.Frame;
import com.segware.segwaretcpserver.model.data.TextMessage;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class A1Request extends Command implements CommandRequest {
    private Logger minaLogger = LoggerFactory.getLogger("org.apache.mina");

    private CommandRepository commandRepository;
    private TextMessage textMessage;

    public A1Request(TextMessage textMessage, CommandRepository commandRepository) {
        super(Frame.TEXT_MESSAGE, new Data(textMessage.toByteArray()));
        this.textMessage = textMessage;
        this.commandRepository = commandRepository;
    }

    public A1Request(Data data, CommandRepository commandRepository) {
        super(Frame.TEXT_MESSAGE, data);
        this.textMessage = TextMessage.fromData(data);
        this.commandRepository = commandRepository;
    }

    @Override
    public void execute(IoSession session) {
        A0Response a0Response = new A0Response();
        session.write(a0Response);
        minaLogger.info("PDU RESPONSE: " + a0Response.toString());
        commandRepository.persist(this, a0Response);
    }

    public TextMessage getTextMessage() {
        return textMessage;
    }

    @Override
    public String toString() {
        return "A1Request{" +
                "init=" + init +
                ", bytes=" + bytes +
                ", frame=" + frame +
                ", data=" + data +
                ", textMessage=" + textMessage +
                ", crc=" + crc +
                ", end=" + end +
                ", receptionTime=" + receptionTime +
                '}';
    }
}
