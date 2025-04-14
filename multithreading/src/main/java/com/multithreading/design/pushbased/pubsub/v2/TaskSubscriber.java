package com.multithreading.design.pushbased.pubsub.v2;

public class TaskSubscriber implements Subscriber{

    private MessageBroker broker;

    public TaskSubscriber(MessageBroker broker) {
        // subscribe to logs-queue
        this.broker = broker;
        broker.subscribe("tasks-queue", this);
    }

    @Override
    public void receive(Message message) {
        System.out.println("TaskSubscriber receives message: "+ message.content());
    }
}
