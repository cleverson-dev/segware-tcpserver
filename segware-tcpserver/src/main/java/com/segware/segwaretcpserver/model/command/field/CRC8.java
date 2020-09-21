package com.segware.segwaretcpserver.model.command.field;

import java.util.Objects;

public class CRC8 {
    private byte crc;

    public CRC8(byte crc) {
        this.crc = crc;
    }

    public byte toByte() {
        return this.crc;
    }

    public static CRC8 calculateForPDU(Bytes bytes, Frame frame, Data data) {
        byte[] byteArray = new byte[2 + data.getLength()];
        byteArray[0] = bytes.toByte();
        byteArray[1] = frame.toByte();
        System.arraycopy(data.toByteArray(), 0, byteArray, 2, data.getLength());

        return new CRC8(calculate(byteArray));
    }

    public static byte calculate(byte[] byteArray) {
        final byte poly = 0x07;
        byte crc = 0x00;

        for (byte currByte : byteArray) {
            crc ^= currByte;
            for (int i = 0; i < 8; i++) {
                if ((crc & 0x80) != 0)
                    crc = (byte)((crc << 1) ^ poly);
                else
                    crc <<= 1;
            }
        }

        return crc;
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
                "crc=" + String.format("0x%02X", crc) +
                '}';
    }
}
