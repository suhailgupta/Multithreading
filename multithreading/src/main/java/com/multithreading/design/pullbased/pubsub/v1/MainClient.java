package com.multithreading.design.pullbased.pubsub.v1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClient {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        MessageQueue messageQueue = new MessageQueue();

        Publisher publisher1 = new Publisher(messageQueue, "Publisher 1");
        Publisher publisher2 = new Publisher(messageQueue, "Publisher 2");
        Subscriber subscriber1 = new Subscriber(messageQueue, "Subscriber 1");
        Subscriber subscriber2 = new Subscriber(messageQueue, "Subscriber 2");

        executorService.execute(publisher1);
        executorService.execute(subscriber1);
        executorService.execute(subscriber2);
        executorService.execute(publisher2);

        try {
            Thread.sleep(5000); //
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        messageQueue.stop();
        executorService.shutdown();


    }
}
