package com.segware.pdu.structure.data;

import java.util.Objects;

public class Height {
    private int height;

    public Height(int height) {
        this.height = height;
    }

    public byte toByte() {
        return (byte) height;
    }

    public int asInt() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Height height1 = (Height) o;
        return height == height1.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height);
    }

    @Override
    public String toString() {
        return "Height{" +
                "height=" + height +
                '}';
    }
}
