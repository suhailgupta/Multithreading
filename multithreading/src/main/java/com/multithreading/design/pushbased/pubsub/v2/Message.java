package com.multithreading.design.pushbased.pubsub.v2;

import java.util.UUID;

public class Message {

    private UUID id;
    private String content;
    private String routingKey;

    public UUID id() {
        return id;
    }

    public String content() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public Message(String content, String routingKey) {
        this.id = UUID.randomUUID();
        this.content = content;
        this.routingKey = routingKey;
    }
}
