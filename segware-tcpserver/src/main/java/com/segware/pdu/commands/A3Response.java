package com.segware.pdu.commands;

import com.segware.pdu.ProtocolDataUnit;
import com.segware.pdu.structure.Data;
import com.segware.pdu.structure.Frame;
import com.segware.pdu.structure.data.DateTime;

public class A3Response extends ProtocolDataUnit implements PDUResponse {
    private DateTime dateTime;

    protected A3Response(DateTime dateTime) {
        super(Frame.CURRENT_DATE_TIME, new Data(dateTime.toByteArray()));
        this.dateTime = dateTime;
    }

    public DateTime getDateTime() {
        return dateTime;
    }
}
