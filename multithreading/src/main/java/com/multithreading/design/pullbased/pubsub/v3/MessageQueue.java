package com.multithreading.design.pullbased.pubsub.v3;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MessageQueue {
    private BlockingQueue<Object> queue = new LinkedBlockingQueue<>();

    public void publish(Object message){
        queue.add(message);
    }

    public Object subscribe() throws InterruptedException{
        return queue.poll(2, TimeUnit.SECONDS);
    }



}
