package com.multithreading.design.pushbased.pubsub.v1;

import java.util.UUID;

public class Message {

    private UUID id;
    private String content;
    private String key;

    public UUID id() {
        return id;
    }

    public String content() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String key() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Message(String content, String key) {
        this.id = UUID.randomUUID();
        this.content = content;
        this.key = key;
    }
}
