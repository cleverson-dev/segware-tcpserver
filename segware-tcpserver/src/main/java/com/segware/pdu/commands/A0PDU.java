package com.segware.pdu.commands;

import com.segware.pdu.ProtocolDataUnit;
import com.segware.pdu.structure.Frame;

public class A0PDU extends ProtocolDataUnit {
    public A0PDU() {
        super(Frame.ACK);
    }
}
