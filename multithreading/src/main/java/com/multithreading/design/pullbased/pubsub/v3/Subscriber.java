package com.multithreading.design.pullbased.pubsub.v3;

public abstract class Subscriber<T> implements Runnable{

    private MessageBroker messageBroker;
    private MessageQueue messageQueue;
    protected String name;

    public Subscriber(MessageBroker messageBroker, String name) {
        this.messageQueue = messageBroker.getMessageQueue(name);
        this.messageBroker = messageBroker;
        this.name = name;
    }

    public abstract void onMessageReceived(T message);


    @Override
    public void run() {
        try {
            while (messageBroker.isRunning()){
                T message = (T) messageQueue.subscribe();
                if(message != null){
                    // triger the callback
                    onMessageReceived(message);
                }
                Thread.sleep(500);
            }
            System.out.println(name + " stopped gracefully");
        } catch (InterruptedException e) {
            System.out.println("Exception occured in "+ name + " - "+ e.getMessage());
        }
    }
}
