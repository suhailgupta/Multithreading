package com.multithreading.design.pullbased.pubsub.v3;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Publisher<T> implements Runnable{

    private MessageBroker messageBroker;
    private MessageQueue messageQueue;
    private Queue<T> publisherQueue;

    protected String name;

    public Publisher(MessageBroker messageBroker, String name) {
        this.messageBroker = messageBroker;
        this.publisherQueue = new ConcurrentLinkedQueue<>();
        this.messageQueue = messageBroker.getMessageQueue(name);
        this.name = name;
    }

    public void publish(T message){
        if(messageBroker.isRunning()){
            System.out.println(name + " publishing "+ message);
            publisherQueue.add(message);
        }
    }

    public abstract void onMessagePublish(T message);

    @Override
    public void run() {

        try {
            while (messageBroker.isRunning() || !publisherQueue.isEmpty()){
                T message = publisherQueue.poll();
                if(message != null){
                    // publish the message to the broker or to the actual queue.
                    messageQueue.publish(message);
                    // callback has to be trigerred.
                    onMessagePublish(message);
                }
                Thread.sleep(400);
            }
        } catch (InterruptedException e) {
            System.out.println("Exception occured in "+ name + " - "+ e.getMessage());
        }
    }
}
