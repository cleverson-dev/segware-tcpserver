package com.segware.segwaretcpserver.segwaretcpserver.model.data;

import java.util.Objects;

public class Name {
    public static final int MAX_LENGTH = 246;
    private static final String NAME_TO_BIG_MESSAGE = "The informed name is too big.";

    private String name;

    public Name(String name) {
        if (isLengthValid(name))
            this.name = name;
        else
            throw new IllegalArgumentException(NAME_TO_BIG_MESSAGE);
    }

    public Name(byte[] name) {
        if (isLengthValid(name))
            this.name = new String(name);
        else
            throw new IllegalArgumentException(NAME_TO_BIG_MESSAGE);
    }

    private boolean isLengthValid(String name) {
        return isLengthValid(name.length());
    }

    private boolean isLengthValid(byte[] name) {
        return isLengthValid(name.length);
    }

    private boolean isLengthValid(int length) {
        return (length >= 0) && (length <= MAX_LENGTH) ? true : false;
    }

    public byte getLengthToByte() {
        return (byte) name.length();
    }

    public int getLengthAsInt() {
        return name.length();
    }

    public byte[] toByteArray() {
        return name.getBytes();
    }

    public String asString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\'' +
                '}';
    }
}
