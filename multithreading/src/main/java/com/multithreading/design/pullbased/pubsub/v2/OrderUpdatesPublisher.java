package com.multithreading.design.pullbased.pubsub.v2;

public class OrderUpdatesPublisher extends  Publisher{

    public OrderUpdatesPublisher(MessageBroker messageBroker, String name) {
        super(messageBroker, name);
    }

    @Override
    public void onMessagePublish(String message) {
        System.out.println(name + " custom published "+ message);
    }

    public void publishMessage(String message){
        publish(message);
    }

}
