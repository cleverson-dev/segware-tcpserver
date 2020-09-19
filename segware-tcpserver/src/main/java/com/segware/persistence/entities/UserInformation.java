package com.segware.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name="USER_INFORMATION")
public class UserInformation {
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

    @OneToOne(mappedBy = "userInformation")
    private A2Request a2Request;

    public UserInformation(com.segware.pdu.structure.data.UserInformation userInformation) {
        age = userInformation.getAge().asInt();
        weight = userInformation.getWeight().asInt();
        height = userInformation.getHeight().asInt();
        name = userInformation.getName().asString();
    }
}
