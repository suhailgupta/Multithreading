package com.multithreading.design.pullbased.pubsub.v3;

public class OrderUpdatesPublisher<T> extends Publisher<T> {

    public OrderUpdatesPublisher(MessageBroker messageBroker, String name) {
        super(messageBroker, name);
    }

    @Override
    public void onMessagePublish(T message) {
        System.out.println(name + " custom published "+ message);
    }


}
