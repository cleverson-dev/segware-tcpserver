package com.segware.pdu.structure;

import com.segware.pdu.ProtocolDataUnit;
import com.segware.pdu.commands.A0PDU;
import com.segware.pdu.commands.A1PDU;
import com.segware.pdu.commands.A2PDU;

public enum Frame {
    ACK((byte) 0xA0) {
        @Override
        public ProtocolDataUnit getPDUInstance(Data data) {
            return new A0PDU(data);
        }
    },
    TEXT_MESSAGE((byte) 0xA1) {
        @Override
        public ProtocolDataUnit getPDUInstance(Data data) {
            return new A1PDU(data);
        }
    },
    USER_INFORMATION((byte) 0xA2) {
        @Override
        public ProtocolDataUnit getPDUInstance(Data data) {
            return new A2PDU(data);
        }
    },
    GET_CURRENT_DATE_TIME((byte) 0xA3) {
        @Override
        public ProtocolDataUnit getPDUInstance(Data data) {
            return null;
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
}
