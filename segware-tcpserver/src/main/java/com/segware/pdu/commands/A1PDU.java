package com.segware.pdu.commands;

import com.segware.pdu.ProtocolDataUnit;
import com.segware.pdu.structure.Data;
import com.segware.pdu.structure.Frame;

public class A1PDU extends ProtocolDataUnit {
    public A1PDU(Data data) {
        super(Frame.TEXT_MESSAGE, data);
    }
}
