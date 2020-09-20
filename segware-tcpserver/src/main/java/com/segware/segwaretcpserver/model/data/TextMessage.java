package com.segware.segwaretcpserver.model.data;

import com.segware.segwaretcpserver.model.command.field.Data;

import java.util.Objects;

public class TextMessage {
    private String textMsg;

    public TextMessage(String textMsg) {
        this.textMsg = textMsg;
    }

    public String getTextMsg() {
        return textMsg;
    }

    public static TextMessage fromData(Data data) {
        return new TextMessage(new String(data.toByteArray()));
    }

    public byte[] toByteArray() {
        return textMsg.getBytes();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextMessage that = (TextMessage) o;
        return Objects.equals(textMsg, that.textMsg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textMsg);
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "textMsg='" + textMsg + '\'' +
                '}';
    }
}
