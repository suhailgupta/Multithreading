package com.multithreading.design.pullbased.pubsub.v2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessageBroker {

    private volatile boolean running = true;

//    <k,V>, k= queueName, V= MessageQueue
    private Map<String, MessageQueue> queueMap = new ConcurrentHashMap<>();

    public MessageQueue getMessageQueue(String name){
        return queueMap.computeIfAbsent(name, k-> new MessageQueue());
    }

    // trigerred by the user.
    public void stop(){
        running = false;
    }

    public boolean isRunning(){
        return running;
    }

}
