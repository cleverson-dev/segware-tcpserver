package com.segware.segwaretcpserver.gateway.database.entitiy;

import com.segware.segwaretcpserver.model.data.TextMessage;

import javax.persistence.*;

@Entity
@Table(name="TEXT_MESSAGE")
public class TextMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEXT_MESSAGE")
    @SequenceGenerator(name = "SEQ_TEXT_MESSAGE", sequenceName = "SEQ_TEXT_MESSAGE", allocationSize = 1)
    @Column(name="TEXT_MESSAGE_ID")
    private long textMessageId;

    @Column(name="TEXT_MSG")
    private String textMsg;

    @OneToOne(mappedBy = "textMessageEntity")
    private A1RequestEntity a1RequestEntity;

    public TextMessageEntity(TextMessage textMessage, A1RequestEntity a1RequestEntity) {
        this.textMsg = textMessage.getTextMsg();
        this.a1RequestEntity = a1RequestEntity;
    }
}
