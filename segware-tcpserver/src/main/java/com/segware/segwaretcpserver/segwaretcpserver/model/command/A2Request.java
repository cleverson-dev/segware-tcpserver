package com.segware.segwaretcpserver.segwaretcpserver.model.command;

import com.segware.segwaretcpserver.segwaretcpserver.model.command.field.Data;
import com.segware.segwaretcpserver.segwaretcpserver.model.command.field.Frame;
import com.segware.segwaretcpserver.segwaretcpserver.model.data.UserInformation;
import com.segware.segwaretcpserver.segwaretcpserver.gateway.database.entities.A2RequestEntity;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class A2Request extends ProtocolDataUnit implements PDURequest {
    private Logger minaLogger = LoggerFactory.getLogger("org.apache.mina");

    private UserInformation userInformation;

    public A2Request(UserInformation data) {
        super(Frame.USER_INFORMATION, new Data(data.toByteArray()));
        this.userInformation = data.deepClone();
    }

    public A2Request(Data data) {
        super(Frame.USER_INFORMATION, data);
        userInformation = UserInformation.fromData(data);
    }

    @Override
    public void execute(IoSession session) {
        A0Response a0Response = new A0Response();
        session.write(a0Response);
        minaLogger.info("PDU RESPONSE: " + a0Response.toString());
        new A2RequestEntity(this, a0Response).persist();
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }
}
