package com.segware.segwaretcpserver.model.command.field;

import java.util.Objects;

public class Init {
    private static final byte INIT_VALUE = (byte) 0x0A;

    private static final Init init = new Init();

    private Init() { }

    public static Init getInstance() {
        return init;
    }

    public byte toByte() {
        return INIT_VALUE;
    }

    public static boolean isValid(byte init) {
        return init == INIT_VALUE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return !(o == null || getClass() != o.getClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(INIT_VALUE);
    }

    @Override
    public String toString() {
        return "Init{" +
                "value=" + String.format("0x%02X", INIT_VALUE) +
                '}';
    }
}
