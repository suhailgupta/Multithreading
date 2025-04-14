package com.multithreading.design.pushbased.pubsub.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Publisher {

    private volatile boolean running = true;
    private List<Subscriber> subscribers = new ArrayList<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private BlockingQueue<Message> queue = new LinkedBlockingQueue<>(100);
    private Map<String, CompletableFuture<Message>> callbacks = new ConcurrentHashMap<>();

    public Publisher(){
        startProcessing();
    }

    public void subscribe(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber){
        subscribers.remove(subscriber);
    }

    public CompletableFuture<Message> publish(Message message){
        CompletableFuture<Message> completableFuture = new CompletableFuture<>();
        callbacks.put(message.id().toString(), completableFuture);
        queue.add(message);
        return completableFuture;
    }

    private void startProcessing(){
        new Thread(() -> {
            while(running || !queue.isEmpty()){
                try {
                    Message message = queue.poll(1, TimeUnit.SECONDS);
                    if(message != null){
                        for (Subscriber subscriber: subscribers){
                            // Apply the filter here
                            if(subscriber.getFilter().test(message)){
                                // this callback has to be executed by the subscriber when it recieves the message
                                executorService.submit(() -> subscriber.receive(message, () -> {
                                    CompletableFuture<Message> completableFuture = callbacks.remove(message.id().toString());
                                    if(completableFuture != null){
                                        completableFuture.complete(message);
                                    }
                                }));
                            }

                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("Exception occured while waiting for messages "+ e);
                    Thread.currentThread().interrupt();
                    break;
                }
            }

        }).start();
    }

    public void shutdown(){
        running = false;
        executorService.shutdown();
    }



}
