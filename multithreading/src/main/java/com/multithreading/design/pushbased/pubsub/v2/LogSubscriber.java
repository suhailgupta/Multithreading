package com.multithreading.design.pushbased.pubsub.v2;

public class LogSubscriber implements Subscriber{

    private MessageBroker broker;

    public LogSubscriber(MessageBroker broker) {
        // subscribe to logs-queue
        this.broker = broker;
        broker.subscribe("logs-queue", this);
    }

    @Override
    public void receive(Message message) {
        System.out.println("LogSubscriber receives message: "+ message.content());
    }
}
