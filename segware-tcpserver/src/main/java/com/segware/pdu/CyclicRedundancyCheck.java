package com.segware.pdu;

public class CyclicRedundancyCheck {
    private byte crc;

    private CyclicRedundancyCheck(byte crc) {
        this.crc = crc;
    }

    public static CyclicRedundancyCheck fromByte(byte crc) {
        return new CyclicRedundancyCheck(crc);
    }

    public byte toByte() {
        return this.crc;
    }
}
