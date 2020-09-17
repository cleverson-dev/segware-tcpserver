package com.segware.pdu;

public enum Frame {
    ACK((byte) 0xA0),
    TEXT_MESSAGE((byte) 0xA1) {
        @Override
        public boolean hasData() {
            return true;
        }
    },
    USER_INFORMATION((byte) 0xA2) {
        @Override
        public boolean hasData() {
            return true;
        }
    },
    GET_CURRENT_DATE_TIME((byte) 0xA3) {
        @Override
        public boolean hasData() {
            return true;
        }
    },
    ;

    private byte code;

    Frame(byte code) {
        this.code = code;
    }

    public boolean hasData() {
        return false;
    }

    public byte getCode() {
        return code;
    }

    public byte toByte() {
        return code;
    }
}
