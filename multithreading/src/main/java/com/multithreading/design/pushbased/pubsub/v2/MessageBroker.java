package com.multithreading.design.pushbased.pubsub.v2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MessageBroker {

    private Map<String,Exchange> exchanges = new HashMap<>();
    private Map<String, BlockingQueue<Message>> queues = new HashMap<>();
    private volatile boolean running = true;

    public void createQueue(String queueName){
        queues.putIfAbsent(queueName, new LinkedBlockingQueue<>());
    }

    public void createExchange(String name, ExchangeType type){
        exchanges.putIfAbsent(name, new Exchange(name, type));
    }

    public void bindQueueToExchange(String queueName,String exchangeName, String routingKey){
        Exchange exchange = exchanges.get(exchangeName);
        if(exchange != null){
            exchange.bindQueueToExchange(queueName, routingKey);
        }
    }

    public void publish(String exchangeName, Message message){
        Exchange exchange = exchanges.get(exchangeName);
        if(exchange != null){
            List<String> matchedQueues = exchange.route(message.getRoutingKey());
            for (String queue: matchedQueues){
                queues.get(queue).add(message);
            }
        }
    }

    public void subscribe(String queueName, Subscriber subscriber){
        new Thread(() -> {
            BlockingQueue<Message> blockingQueue = queues.get(queueName);
            while(running){
                try {
                    Message message = blockingQueue.poll(1, TimeUnit.SECONDS);
                    if(message != null){
                        subscriber.receive(message);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Exception occured while waiting for messages "+ e);
                    Thread.currentThread().interrupt();
                    break;
                }
            }

        }).start();

    }

    public void shutDown(){
        running = false;
    }

}
