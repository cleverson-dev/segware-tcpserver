package com.segware.segwaretcpserver.segwaretcpserver.model.command;

import com.segware.segwaretcpserver.segwaretcpserver.model.command.field.Data;
import com.segware.segwaretcpserver.segwaretcpserver.model.command.field.Frame;

public class A0Response extends ProtocolDataUnit implements PDUResponse {
    public A0Response() {
        super(Frame.ACK, new Data(new byte[0]));
    }

    public A0Response(Data data) {
        super(Frame.ACK, data);
    }
}
