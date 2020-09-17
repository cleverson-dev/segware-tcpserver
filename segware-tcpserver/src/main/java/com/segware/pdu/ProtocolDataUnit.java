package com.segware.pdu;

import com.segware.pdu.structure.*;
import org.apache.mina.core.session.IoSession;

import java.util.Objects;

public abstract class ProtocolDataUnit {
    public static final int FIXED_FIELDS_LENGTH = 5;
    public static final int HEADER_FIELDS_LENGTH = 3;

    protected Init init;
    protected Bytes bytes;
    protected Frame frame;
    protected Data data;
    protected CRC8 crc;
    protected End end;

    protected ProtocolDataUnit(Frame frame, Data data) {
        this.init = Init.getInstance();
        this.frame = frame;
        this.data = data;
        this.bytes = Bytes.fromInt(FIXED_FIELDS_LENGTH + data.getLength());
        this.crc = CRC8.calculate(bytes, frame, data);
        this.end = End.getInstance();
    }

    private int fillData(byte[] data, byte[] pdu, int index) {
        System.arraycopy(data, 0, pdu, index, data.length);
        return index + data.length;
    }

    public abstract void execute(IoSession session);

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
