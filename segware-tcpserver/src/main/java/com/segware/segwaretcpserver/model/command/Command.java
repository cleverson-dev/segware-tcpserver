package com.segware.segwaretcpserver.model.command;

import com.segware.segwaretcpserver.model.command.field.*;

import java.time.LocalDateTime;
import java.util.Objects;

public class Command {
    public static final int FIXED_FIELDS_LENGTH = 5;
    public static final int HEADER_FIELDS_LENGTH = 3;

    protected Init init;
    protected Bytes bytes;
    protected Frame frame;
    protected Data data;
    protected CRC8 crc;
    protected End end;

    protected LocalDateTime receptionTime;

    protected Command(Frame frame, Data data) {
        this.init = Init.getInstance();
        this.frame = frame;
        this.data = data;
        this.bytes = new Bytes(FIXED_FIELDS_LENGTH + data.getLength());
        this.crc = CRC8.calculateForPDU(bytes, frame, data);
        this.end = End.getInstance();

        this.receptionTime = LocalDateTime.now();
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

    public LocalDateTime getReceptionTime() {
        return receptionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command that = (Command) o;
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
        return "Command{" +
                "init=" + init +
                ", bytes=" + bytes +
                ", frame=" + frame +
                ", data=" + data +
                ", crc=" + crc +
                ", end=" + end +
                ", receptionTime=" + receptionTime +
                '}';
    }
}
