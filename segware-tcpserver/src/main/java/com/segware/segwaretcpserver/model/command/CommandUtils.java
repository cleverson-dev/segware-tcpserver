package com.segware.segwaretcpserver.model.command;

public class CommandUtils {
    public static int asInt(byte b) {
        return b & 0xFF;
    }

    public static boolean canBeCastedToByte(int value) {
        return (value >= 0) && (value <= 255) ? true : false;
    }
}
