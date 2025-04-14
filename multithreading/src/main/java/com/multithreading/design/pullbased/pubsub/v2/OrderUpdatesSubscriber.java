package com.multithreading.design.pullbased.pubsub.v2;

public class OrderUpdatesSubscriber extends Subscriber{

    public OrderUpdatesSubscriber(MessageBroker messageBroker, String name) {
        super(messageBroker, name);
    }

    @Override
    public void onMessageReceived(String message) {
        System.out.println(name + " custom received "+ message);
    }
}
