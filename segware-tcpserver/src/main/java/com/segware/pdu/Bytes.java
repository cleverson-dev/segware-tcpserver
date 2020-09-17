package com.segware.pdu;

public class Bytes {
    private byte bytes;

    private Bytes(byte bytes) {
        this.bytes = bytes;
    }

    public static Bytes fromInt(int pduLength) {
        return new Bytes((byte) pduLength);
    }

    public int asInt() {
        return bytes;
    }

    public byte toByte() {
        return bytes;
    }
}
