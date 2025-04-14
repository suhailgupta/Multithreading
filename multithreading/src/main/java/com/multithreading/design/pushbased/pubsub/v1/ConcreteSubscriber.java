package com.multithreading.design.pushbased.pubsub.v1;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class ConcreteSubscriber implements Subscriber{

    private String name;
    private Predicate<Message> predicate;

    private BiConsumer<Message, Subscriber> biConsumer;

    public ConcreteSubscriber(String name, Predicate<Message> predicate, BiConsumer<Message, Subscriber> biConsumer) {
        this.name = name;
        this.predicate = predicate;
        this.biConsumer = biConsumer;
    }

    @Override
    public Predicate<Message> getFilter() {
        return predicate;
    }

    @Override
    public void receive(Message message, Runnable runnable) {
        System.out.println(name + " received "+ message.content());
        runnable.run();// acknowledgment to publisher
        // execute the client specific behavior at subscriber side.
        biConsumer.accept(message, this);
    }
}
