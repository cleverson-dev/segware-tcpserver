package com.segware.pdu.structure;

import java.util.Objects;

public class Bytes {
    private byte bytes;

    // TODO: constructor always with int to avoid problems with the signal of the type
    public Bytes(byte bytes) {
        this.bytes = bytes;
    }

    public static Bytes fromInt(int pduLength) {
        return new Bytes((byte) pduLength);
    }

    // TODO: convert numbers grater than 127 to int
    public int asInt() {
        return bytes;
    }

    public byte toByte() {
        return bytes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bytes bytes1 = (Bytes) o;
        return bytes == bytes1.bytes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bytes);
    }

    @Override
    public String toString() {
        return "Bytes{" +
                "bytes=" + bytes +
                '}';
    }
}
