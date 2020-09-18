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
                "data=" + toHexString(data) +
                '}';
    }

    private String toHexString(byte[] data) {
        StringBuffer hexString = new StringBuffer();

        hexString.append(String.format("0x%02X", data[0]));
        for (int index = 1; index < data.length; index++)
            hexString.append(String.format(", 0x%02X", data[index]));

        return hexString.toString();
    }
}
