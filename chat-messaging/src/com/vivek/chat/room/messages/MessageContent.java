package com.vivek.chat.room.messages;

public class MessageContent {

    private ContentType type;
    private String content; // if type==Image, Voice, Video => URL else Text

    public MessageContent(ContentType type, String content) {
        this.type = type;
        this.content = content;
    }

    public ContentType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
