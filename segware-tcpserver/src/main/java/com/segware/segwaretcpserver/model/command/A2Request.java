package com.segware.segwaretcpserver.model.command;

import com.segware.segwaretcpserver.model.command.field.Data;
import com.segware.segwaretcpserver.model.command.field.Frame;
import com.segware.segwaretcpserver.model.data.UserInformation;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class A2Request extends ProtocolDataUnit implements PDURequest {
    private Logger minaLogger = LoggerFactory.getLogger("org.apache.mina");

    private CommandRepository commandRepository;
    private UserInformation userInformation;

    public A2Request(UserInformation data, CommandRepository commandRepository) {
        super(Frame.USER_INFORMATION, new Data(data.toByteArray()));
        this.userInformation = data.deepClone();
        this.commandRepository = commandRepository;
    }

    public A2Request(Data data, CommandRepository commandRepository) {
        super(Frame.USER_INFORMATION, data);
        userInformation = UserInformation.fromData(data);
        this.commandRepository = commandRepository;
    }

    @Override
    public void execute(IoSession session) {
        A0Response a0Response = new A0Response();
        session.write(a0Response);
        minaLogger.info("PDU RESPONSE: " + a0Response.toString());
        commandRepository.persist(this, a0Response);
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }
}
