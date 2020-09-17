package com.segware.pdu;

import java.util.Arrays;

public class ProtocolDataUnit {
    public static final int FIXED_FIELDS_LENGTH = 5;

    private Init init;
    private Bytes bytes;
    private Frame frame;
    private Data data;
    private CyclicRedundancyCheck crc;
    private End end;

    private ProtocolDataUnit() {
        this.init = new Init();
        this.end = new End();
    }

    protected ProtocolDataUnit(Frame frame) {
        this();

        this.frame = frame;
        this.bytes = Bytes.fromInt(FIXED_FIELDS_LENGTH);
        this.calculateCRC();
    }

    protected ProtocolDataUnit(Frame frame, Data data) {
        this();

        this.frame = frame;
        this.data = data;
        this.bytes = Bytes.fromInt(FIXED_FIELDS_LENGTH + data.getLength());
        this.calculateCRC();
    }

    //TODO
    private CyclicRedundancyCheck calculateCRC() {
        return CyclicRedundancyCheck.fromByte((byte) 0);
    }

    public static ProtocolDataUnit withoutData(Frame frame) {
        if (frame.hasData())
            throw new RuntimeException("This PDU can't be instantiated without data. " +
                    "Use the proper constructor.");

        return new ProtocolDataUnit(frame);
    }

    public static ProtocolDataUnit withData(Frame frame, Data data) {
        return new ProtocolDataUnit(frame, data);
    }

    public byte[] toByteArray() {
        byte[] byteArray = new byte[bytes.asInt()];

        int index = 0;
        byteArray[index++] = init.toByte();
        byteArray[index++] = bytes.toByte();
        byteArray[index++] = frame.toByte();
        index = fillData(data.toByteArray(), byteArray, index);
        byteArray[index++] = crc.toByte();
        byteArray[index++] = end.toByte();

        return byteArray;
    }

    private int fillData(byte[] data, byte[] pdu, int index) {
        System.arraycopy(data, 0, pdu, index, data.length);
        return index + data.length;
    }
}
