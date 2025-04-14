package com.multithreading.design.pushbased.pubsub.v2;


public class Publisher {

    private MessageBroker broker;
    private String exchangeName;


    public Publisher(String exchangeName, MessageBroker broker){
        this.broker =broker;
        this.exchangeName = exchangeName;
    }

    public void publish(String content, String routingKey){
        Message message = new Message(content, routingKey);
        broker.publish(exchangeName, message);
    }
}
