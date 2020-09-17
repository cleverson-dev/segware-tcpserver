package com.segware.pdu.structure;

import java.util.Objects;

public class CRC8 {
    private byte crc;

    public CRC8(byte crc) {
        this.crc = crc;
    }

    public byte toByte() {
        return this.crc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CRC8 crc8 = (CRC8) o;
        return crc == crc8.crc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(crc);
    }

    @Override
    public String toString() {
        return "CRC8{" +
                "crc=" + crc +
                '}';
    }
}
