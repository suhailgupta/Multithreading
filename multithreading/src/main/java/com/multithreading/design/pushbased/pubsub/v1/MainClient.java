package com.multithreading.design.pushbased.pubsub.v1;

import java.util.function.BiConsumer;

public class MainClient {

    public static void main(String[] args) {

        BiConsumer<Message, Subscriber> infoConsumer = (msg, sub) ->{
            System.out.println("Processed INFO Message "+ msg.content());
        };
        BiConsumer<Message, Subscriber> errorConsumer = (msg, sub) ->{
            System.out.println("Processed ERROR Message "+ msg.content());
        };

        ConcreteSubscriber sub1 = new ConcreteSubscriber("Subscriber 1", message -> message.key().equals("INFO"), infoConsumer);
        ConcreteSubscriber sub2 = new ConcreteSubscriber("Subscriber 2", message -> message.key().equals("ERROR"), errorConsumer);


        Publisher publisher = new Publisher();
        publisher.subscribe(sub1);
        publisher.subscribe(sub2);


        for (int i = 1; i <=5 ; i++) {
            Message infoMsg = new Message("INFO MESSAGE "+ i, "INFO");
            Message errorMsg = new Message("ERROR MESSAGE "+ i, "ERROR");
            publisher.publish(infoMsg).thenAccept(msg -> System.out.println("Message Delivered "+ msg.content()));
            publisher.publish(errorMsg).thenAccept(msg -> System.out.println("Message Delivered "+ msg.content()));
        }

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        publisher.shutdown();
    }
}
