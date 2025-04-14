package com.multithreading.design.pullbased.pubsub.v1;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MessageQueue {
    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private volatile boolean running = true;

    public void publish(String message){
        queue.add(message);
    }

    public String subscribe() throws InterruptedException{
        return queue.poll(2, TimeUnit.SECONDS);
    }

    // trigerred by the user.
    public void stop(){
        running = false;
    }

    public boolean isRunning(){
        return running;
    }

}
