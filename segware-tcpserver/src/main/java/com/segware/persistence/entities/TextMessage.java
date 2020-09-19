package com.segware.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name="TEXT_MESSAGE")
public class TextMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEXT_MESSAGE")
    @SequenceGenerator(name = "SEQ_TEXT_MESSAGE", sequenceName = "SEQ_TEXT_MESSAGE", allocationSize = 1)
    @Column(name="TEXT_MESSAGE_ID")
    private long textMessageId;

    @Column(name="TEXT_MSG")
    private String textMsg;

    @OneToOne(mappedBy = "textMessage")
    private A1Request a1Request;

    public TextMessage(byte[] textMsg) {
        this.textMsg = new String(textMsg);
    }
}
