package com.segware.segwaretcpserver.model.command;

import com.segware.segwaretcpserver.model.command.field.Data;
import com.segware.segwaretcpserver.model.command.field.Frame;
import com.segware.segwaretcpserver.model.data.DateTime;

public class A3Response extends Command implements CommandResponse {
    private DateTime dateTime;

    protected A3Response(DateTime dateTime) {
        super(Frame.CURRENT_DATE_TIME, new Data(dateTime.toByteArray()));
        this.dateTime = dateTime;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "A3Response{" +
                "init=" + init +
                ", bytes=" + bytes +
                ", frame=" + frame +
                ", data=" + data +
                ", dateTime=" + dateTime +
                ", crc=" + crc +
                ", end=" + end +
                ", receptionTime=" + receptionTime +
                '}';
    }
}
