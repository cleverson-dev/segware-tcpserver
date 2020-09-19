package com.segware.pdu.structure.data;

import java.util.Objects;

public class Weight {
    private int weight;

    public Weight(int weight) {
        this.weight = weight;
    }

    public byte toByte() {
        return (byte) weight;
    }

    public int asInt() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weight weight1 = (Weight) o;
        return weight == weight1.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight);
    }

    @Override
    public String toString() {
        return "Weight{" +
                "weight=" + weight +
                '}';
    }
}
