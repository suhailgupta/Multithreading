package com.multithreading.design.pullbased.pubsub.v3;

public class OrderUpdatesSubscriber<T> extends Subscriber<T> {

    public OrderUpdatesSubscriber(MessageBroker messageBroker, String name) {
        super(messageBroker, name);
    }

    @Override
    public void onMessageReceived(T message) {
        System.out.println(name + " custom received "+ message);
    }
}
