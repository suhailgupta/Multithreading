package com.multithreading.design.pullbased.pubsub.v1;

public class Subscriber implements Runnable{

    private MessageQueue messageQueue;
    private String name;

    public Subscriber(MessageQueue messageQueue, String name) {
        this.messageQueue = messageQueue;
        this.name = name;
    }


    @Override
    public void run() {
        try {
            while (messageQueue.isRunning()){
                String message = messageQueue.subscribe();
                if(message != null){
                    System.out.println(name + " consumed "+ message);
                }
                Thread.sleep(50);
            }
            System.out.println(name + " stopped gracefully");
        } catch (InterruptedException e) {
            System.out.println("Exception occured in "+ name + " - "+ e.getMessage());
        }
    }
}
