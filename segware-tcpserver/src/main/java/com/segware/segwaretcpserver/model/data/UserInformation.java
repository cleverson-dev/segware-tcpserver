package com.segware.segwaretcpserver.model.data;

import com.segware.segwaretcpserver.model.command.field.Data;

import java.util.Objects;

import static com.segware.segwaretcpserver.model.command.PDUUtils.asInt;

public class UserInformation {
    public static final int FIXED_FIELDS_LENGTH = 4;

    private Age age;
    private Weight weight;
    private Height height;
    private Name name;

    public UserInformation(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public static UserInformation fromData(Data data) {
        byte[] dataArray = data.toByteArray();

        int index = 0;
        int age = asInt(dataArray[index++]);
        int weight = asInt(dataArray[index++]);
        int height = asInt(dataArray[index++]);
        int nameLength = asInt(dataArray[index++]);
        byte[] name = new byte[nameLength];
        System.arraycopy(dataArray, index, name, 0, nameLength);

        UserInformation userInformation = new UserInformation(new Name(name));
        userInformation.setAge(new Age(age));
        userInformation.setWeight(new Weight(weight));
        userInformation.setHeight(new Height(height));

        return userInformation;
    }

    public byte[] toByteArray() {
        byte[] byteArray = new byte[FIXED_FIELDS_LENGTH + name.getLengthAsInt()];

        int index = 0;
        byteArray[index++] = age.toByte();
        byteArray[index++] = weight.toByte();
        byteArray[index++] = height.toByte();
        byteArray[index++] = name.getLengthToByte();
        System.arraycopy(name.toByteArray(), 0, byteArray, index, name.getLengthAsInt());

        return byteArray;
    }

    public UserInformation deepClone() {
        UserInformation userInformation = new UserInformation(name);
        userInformation.setAge(age);
        userInformation.setHeight(height);
        userInformation.setWeight(weight);
        return userInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInformation that = (UserInformation) o;
        return Objects.equals(age, that.age) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(height, that.height) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, weight, height, name);
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", name=" + name +
                '}';
    }
}
