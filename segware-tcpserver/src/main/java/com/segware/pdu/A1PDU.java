package com.segware.pdu;

public class A1PDU extends ProtocolDataUnit {
    public A1PDU(Data data) {
        super(Frame.TEXT_MESSAGE, data);
    }
}
