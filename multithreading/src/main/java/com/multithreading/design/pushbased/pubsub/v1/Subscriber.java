package com.multithreading.design.pushbased.pubsub.v1;

import java.util.function.Predicate;

public interface Subscriber {

    void receive(Message message, Runnable runnable);

    Predicate<Message> getFilter();

}
