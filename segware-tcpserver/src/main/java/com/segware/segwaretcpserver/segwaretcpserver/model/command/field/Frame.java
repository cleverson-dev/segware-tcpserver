package com.segware.segwaretcpserver.segwaretcpserver.model.command.field;

import com.segware.segwaretcpserver.segwaretcpserver.model.command.ProtocolDataUnit;
import com.segware.segwaretcpserver.segwaretcpserver.model.command.A0Response;
import com.segware.segwaretcpserver.segwaretcpserver.model.command.A1Request;
import com.segware.segwaretcpserver.segwaretcpserver.model.command.A2Request;
import com.segware.segwaretcpserver.segwaretcpserver.model.command.A3Request;

public enum Frame {
    ACK((byte) 0xA0) {
        @Override
        public ProtocolDataUnit getPDUInstance(Data data) {
            return new A0Response(data);
        }
    },
    TEXT_MESSAGE((byte) 0xA1) {
        @Override
        public ProtocolDataUnit getPDUInstance(Data data) {
            return new A1Request(data);
        }
    },
    USER_INFORMATION((byte) 0xA2) {
        @Override
        public ProtocolDataUnit getPDUInstance(Data data) {
            return new A2Request(data);
        }
    },
    CURRENT_DATE_TIME((byte) 0xA3) {
        @Override
        public ProtocolDataUnit getPDUInstance(Data data) {
            return new A3Request(data);
        }
    },
    ;

    private byte code;

    Frame(byte code) {
        this.code = code;
    }

    public abstract ProtocolDataUnit getPDUInstance(Data data);

    public byte toByte() {
        return code;
    }

    public static Frame fromByte(byte frame) {
        for (Frame iteratorFrame : Frame.values()) {
            if (iteratorFrame.code == frame)
                return iteratorFrame;
        }
        throw new IllegalArgumentException("The informed value is not a recognizable FRAME value.");
    }

    @Override
    public String toString() {
        return "Frame{" +
                "code=" + String.format("0x%02X", code) +
                '}';
    }
}
