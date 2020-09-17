package com.segware.pdu;

public class Data {
    private byte[] data;

    private Data(byte[] byteArray) {
        data = byteArray.clone();
    }

    public static Data fromByteArray(byte[] byteArray) {
        return new Data(byteArray.clone());
    }

    public static Data fromString(String string) {
        return new Data(string.getBytes());
    }

    public int getLength() {
        return this.data.length;
    }

    public byte[] toByteArray() {
        return data.clone();
    }
}
