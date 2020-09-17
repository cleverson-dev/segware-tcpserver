package com.segware.pdu;

import com.segware.pdu.structure.*;

import java.util.Objects;

public class ProtocolDataUnit {
    public static final int FIXED_FIELDS_LENGTH = 5;
    public static final int HEADER_FIELDS_LENGTH = 3;

    private Init init;
    private Bytes bytes;
    private Frame frame;
    private Data data;
    private CRC8 crc;
    private End end;

    private ProtocolDataUnit() {
        this.init = Init.getInstance();
        this.end = End.getInstance();
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
    private CRC8 calculateCRC() {
        return new CRC8((byte) 0);
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

    public int getLength() {
        return this.bytes.asInt();
    }

    public Init getInit() {
        return init;
    }

    public Bytes getBytes() {
        return bytes;
    }

    public Frame getFrame() {
        return frame;
    }

    public Data getData() {
        return data;
    }

    public CRC8 getCrc() {
        return crc;
    }

    public End getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProtocolDataUnit that = (ProtocolDataUnit) o;
        return Objects.equals(init, that.init) &&
                Objects.equals(bytes, that.bytes) &&
                frame == that.frame &&
                Objects.equals(data, that.data) &&
                Objects.equals(crc, that.crc) &&
                Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(init, bytes, frame, data, crc, end);
    }

    @Override
    public String toString() {
        return "ProtocolDataUnit{" +
                "init=" + init +
                ", bytes=" + bytes +
                ", frame=" + frame +
                ", data=" + data +
                ", crc=" + crc +
                ", end=" + end +
                '}';
    }
}
