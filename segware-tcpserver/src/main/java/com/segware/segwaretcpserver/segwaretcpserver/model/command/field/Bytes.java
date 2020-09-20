package com.segware.segwaretcpserver.segwaretcpserver.model.command.field;

import com.segware.segwaretcpserver.segwaretcpserver.model.command.PDUUtils;

import java.util.Objects;

import static com.segware.segwaretcpserver.segwaretcpserver.model.command.PDUUtils.canBeCastedToByte;

public class Bytes {
    private byte bytes;

    public Bytes(byte bytes) {
        this.bytes = bytes;
    }

    public Bytes(int bytes) {
        if (canBeCastedToByte(bytes))
            this.bytes = (byte) bytes;
        else
            throw new IllegalArgumentException("The informed value can't be casted to int.");
    }

    public int asInt() {
        return PDUUtils.asInt(bytes);
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
                "bytes=" + String.format("0x%02X", bytes) +
                '}';
    }
}
