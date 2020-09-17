package com.segware.pdu.structure;

import java.util.Arrays;

public class Data {
    private byte[] data;

    public Data(byte[] byteArray) {
        data = byteArray.clone();
    }

    public static Data fromString(String string) {
        return new Data(string.getBytes());
    }

    public int getLength() {
        return this.data.length;
    }

    public byte[] toByteArray() {
        return data.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data1 = (Data) o;
        return Arrays.equals(data, data1.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public String toString() {
        return "Data{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
