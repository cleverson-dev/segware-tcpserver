package com.segware.pdu;

public class PDUUtils {
    public static int asInt(byte b) {
        return b & 0xFF;
    }
}
