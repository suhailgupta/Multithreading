package com.multithreading.design.pushbased.pubsub.v2;

public class AlertSubscriber implements Subscriber{

    private MessageBroker broker;

    public AlertSubscriber(MessageBroker broker) {
        // subscribe to alerts-queue
        this.broker = broker;
        broker.subscribe("alerts-queue", this);
    }

    @Override
    public void receive(Message message) {
        System.out.println("AlertSubscriber receives message: "+ message.content());
    }
}
