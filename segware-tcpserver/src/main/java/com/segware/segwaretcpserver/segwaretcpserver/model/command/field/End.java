package com.segware.segwaretcpserver.segwaretcpserver.model.command.field;

import java.util.Objects;

public class End {
    private static final byte END_VALUE = 0x0D;

    private static final End end = new End();

    private End() { }

    public static End getInstance() {
        return end;
    }

    public byte toByte() {
        return END_VALUE;
    }

    public static boolean isValid(byte end) {
        return end == END_VALUE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return !(o == null || getClass() != o.getClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(END_VALUE);
    }

    @Override
    public String toString() {
        return "End{" +
                "value=" + String.format("0x%02X", END_VALUE) +
                '}';
    }
}
