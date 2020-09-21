package com.segware.segwaretcpserver.gateway.database.entitiy;

import com.segware.segwaretcpserver.model.data.UserInformation;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="USER_INFORMATION")
public class UserInformationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_INFORMATION")
    @SequenceGenerator(name = "SEQ_USER_INFORMATION", sequenceName = "SEQ_USER_INFORMATION", allocationSize = 1)
    @Column(name="USER_INFORMATION_ID")
    private long userInformationId;

    @Column(name="AGE")
    private int age;

    @Column(name="WEIGHT")
    private int weight;

    @Column(name="HEIGHT")
    private int height;

    @Column(name="NAME")
    private String name;

    @OneToOne(mappedBy = "userInformationEntity")
    private A2RequestEntity a2RequestEntity;

    public UserInformationEntity(UserInformation userInformation, A2RequestEntity a2RequestEntity) {
        age = userInformation.getAge().asInt();
        weight = userInformation.getWeight().asInt();
        height = userInformation.getHeight().asInt();
        name = userInformation.getName().asString();

        this.a2RequestEntity = a2RequestEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInformationEntity that = (UserInformationEntity) o;
        return userInformationId == that.userInformationId &&
                age == that.age &&
                weight == that.weight &&
                height == that.height &&
                Objects.equals(name, that.name) &&
                Objects.equals(a2RequestEntity, that.a2RequestEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userInformationId, age, weight, height, name, a2RequestEntity);
    }

    @Override
    public String toString() {
        return "UserInformationEntity{" +
                "userInformationId=" + userInformationId +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", name='" + name + '\'' +
                ", a2RequestEntity=" + a2RequestEntity +
                '}';
    }
}
