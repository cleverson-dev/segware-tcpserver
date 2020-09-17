package com.segware.pdu.structure.data;

import java.util.Objects;

public class Age {
    private int age;

    public Age(int age) {
        this.age = age;
    }

    public byte toByte() {
        return (byte) age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Age age1 = (Age) o;
        return age == age1.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age);
    }

    @Override
    public String toString() {
        return "Age{" +
                "age=" + age +
                '}';
    }
}
