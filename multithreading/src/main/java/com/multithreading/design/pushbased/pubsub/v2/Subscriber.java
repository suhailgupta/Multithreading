package com.multithreading.design.pushbased.pubsub.v2;

import java.util.function.Predicate;

public interface Subscriber {

    void receive(Message message);


}
