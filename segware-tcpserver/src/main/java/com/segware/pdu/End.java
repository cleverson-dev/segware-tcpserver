package com.segware.pdu;

public class End {
    private byte init = 0x0D;

    public byte toByte() {
        return this.init;
    }
}
