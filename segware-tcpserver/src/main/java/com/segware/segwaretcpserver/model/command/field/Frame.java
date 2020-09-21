package com.segware.segwaretcpserver.model.command.field;

import com.segware.segwaretcpserver.model.command.*;

public enum Frame {
    ACK((byte) 0xA0) {
        @Override
        public Command getCommand(Data data, CommandRepository commandRepository) {
            return new A0Response(data);
        }
    },
    TEXT_MESSAGE((byte) 0xA1) {
        @Override
        public Command getCommand(Data data, CommandRepository commandRepository) {
            return new A1Request(data, commandRepository);
        }
    },
    USER_INFORMATION((byte) 0xA2) {
        @Override
        public Command getCommand(Data data, CommandRepository commandRepository) {
            return new A2Request(data, commandRepository);
        }
    },
    CURRENT_DATE_TIME((byte) 0xA3) {
        @Override
        public Command getCommand(Data data, CommandRepository commandRepository) {
            return new A3Request(data, commandRepository);
        }
    },
    ;

    private byte code;

    Frame(byte code) {
        this.code = code;
    }

    public abstract Command getCommand(Data data, CommandRepository commandRepository);

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
