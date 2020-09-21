package com.segware.segwaretcpserver.model.command;

import com.segware.segwaretcpserver.model.command.field.Data;
import com.segware.segwaretcpserver.model.command.field.Frame;

public class A0Response extends Command implements CommandResponse {
    public A0Response() {
        super(Frame.ACK, new Data(new byte[0]));
    }

    public A0Response(Data data) {
        super(Frame.ACK, data);
        if (data.getLength() != 0)
            throw new IllegalArgumentException("A0 command cannot have a data field.");
    }

    @Override
    public String toString() {
        return "A0Response{" +
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
