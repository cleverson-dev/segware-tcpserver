package com.segware.pdu.commands;

import com.segware.pdu.ProtocolDataUnit;
import com.segware.pdu.structure.Data;
import com.segware.pdu.structure.Frame;
import com.segware.pdu.structure.data.UserInformation;
import org.apache.mina.core.session.IoSession;

public class A2PDU extends ProtocolDataUnit {
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
        session.write(new A0PDU());
    }
}