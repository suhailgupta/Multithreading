package com.multithreading.design.pullbased.pubsub.v1;

public class Publisher implements Runnable{

    private MessageQueue messageQueue;
    private String name;

    public Publisher(MessageQueue messageQueue, String name) {
        this.messageQueue = messageQueue;
        this.name = name;
    }

    public void publish(String message){
        System.out.println(name + " publishing "+ message);
        messageQueue.publish(message);
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <=5 ; i++) {
                String message = "Message "+ i;
                publish(message);
                Thread.sleep(40);
            }
        } catch (InterruptedException e) {
            System.out.println("Exception occured in "+ name + " - "+ e.getMessage());
        }
    }
}
