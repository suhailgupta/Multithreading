package com.multithreading.design.pushbased.pubsub.v2;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class ConcreteSubscriber implements Subscriber {

    private String name;
    private BiConsumer<Message, Subscriber> biConsumer;

    public ConcreteSubscriber(String name, Predicate<Message> predicate, BiConsumer<Message, Subscriber> biConsumer) {
        this.name = name;
        this.biConsumer = biConsumer;
    }

    @Override
    public void receive(Message message) {
        System.out.println(name + " received "+ message.content());
        // execute the client specific behavior at subscriber side.
        biConsumer.accept(message, this);
    }
}
