package com.segware.pdu.commands;

import com.segware.pdu.ProtocolDataUnit;
import com.segware.pdu.structure.Data;
import com.segware.pdu.structure.Frame;
import com.segware.pdu.structure.data.UserInformation;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class A2PDU extends ProtocolDataUnit {
    private Logger minaLogger = LoggerFactory.getLogger("org.apache.mina");

    private UserInformation userInformation;

    public A2PDU(UserInformation data) {
        super(Frame.USER_INFORMATION, new Data(data.toByteArray()));
        this.userInformation = data.deepClone();
    }

    public A2PDU(Data data) {
        super(Frame.USER_INFORMATION, data);
        userInformation = UserInformation.fromData(data);
    }

    @Override
    public void execute(IoSession session) {
        A0PDU a0PDUResponse = new A0PDU();
        session.write(a0PDUResponse);
        minaLogger.info("PDU RESPONSE: " + a0PDUResponse.toString());
    }
}
