package com.multithreading.design.pullbased.pubsub.v2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClient {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        MessageBroker messageBroker = new MessageBroker();

        Publisher orderUpdatesPublisher = new OrderUpdatesPublisher(messageBroker, "Order-Updates");
//        Publisher notifUpdatesPublisher = new Publisher(messageBroker, "Notif-Updates");
        Subscriber orderUpdatesSubscriber = new OrderUpdatesSubscriber(messageBroker, "Order-Updates");
//        Subscriber notifUpdatesSubscriber = new Subscriber(messageBroker, "Notif-Updates");

        executorService.execute(orderUpdatesPublisher);
        executorService.execute(orderUpdatesSubscriber);
//        executorService.execute(notifUpdatesSubscriber);
//        executorService.execute(notifUpdatesPublisher);


        orderUpdatesPublisher.publish("Order 1 placed");
        orderUpdatesPublisher.publish("Order 2 placed");
        orderUpdatesPublisher.publish("Order 3 placed");
        orderUpdatesPublisher.publish("Order 4 placed");

        try {
            Thread.sleep(5000); //
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        messageBroker.stop();
        executorService.shutdown();


    }
}
