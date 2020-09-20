package com.segware.pdu.commands;

import com.segware.pdu.ProtocolDataUnit;
import com.segware.pdu.structure.Data;
import com.segware.pdu.structure.Frame;
import com.segware.pdu.structure.data.UserInformation;
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
        new com.segware.persistence.entities.A2Request(this, a0Response).persist();
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }
}
