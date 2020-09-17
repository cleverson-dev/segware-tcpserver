package com.segware.pdu.structure.data;

import java.util.Objects;

public class Name {
    private String name;

    public Name(String name) {
        this.name = name;
    }

    public Name(byte[] name) {
        this.name = new String(name);
    }

    // TODO: verify lengths handled in bytes for number grater than 127, store int use byte just for pdu
    public byte getLengthToByte() {
        return (byte) name.length();
    }

    public int getLengthAsInt() {
        return name.length();
    }

    public byte[] toByteArray() {
        return name.getBytes();
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
