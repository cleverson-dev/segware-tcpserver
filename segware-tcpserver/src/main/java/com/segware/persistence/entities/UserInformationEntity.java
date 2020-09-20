package com.segware.persistence.entities;

import com.segware.pdu.structure.data.UserInformation;

import javax.persistence.*;

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
}
