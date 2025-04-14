package com.multithreading.design.pullbased.pubsub.v2;

public abstract class Subscriber implements Runnable{

    private MessageBroker messageBroker;
    private MessageQueue messageQueue;
    protected String name;

    public Subscriber(MessageBroker messageBroker, String name) {
        this.messageQueue = messageBroker.getMessageQueue(name);
        this.messageBroker = messageBroker;
        this.name = name;
    }

    public abstract void onMessageReceived(String message);


    @Override
    public void run() {
        try {
            while (messageBroker.isRunning()){
                String message = messageQueue.subscribe();
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
