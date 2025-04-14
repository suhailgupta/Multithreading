package com.multithreading.design.pushbased.pubsub.v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exchange {

    private String name;
    private ExchangeType type;
//    <k,v>, k= routingKey, v= list of queueNames
    private Map<String, List<String>> bindings = new HashMap<>();

    public Exchange(String name, ExchangeType type) {
        this.name = name;
        this.type = type;
    }

    public void bindQueueToExchange(String queue, String routingKey){
        bindings.computeIfAbsent(routingKey, k -> new ArrayList<>()).add(queue);
    }

    public List<String> route(String routingKey){
        List<String> matchedQueues = new ArrayList<>();
        switch (type){
            case FANOUT -> bindings.values().forEach(matchedQueues::addAll);
            case DIRECT -> matchedQueues.addAll(bindings.getOrDefault(routingKey, List.of()));
            case TOPIC -> {
                for (var entry: bindings.entrySet()){
                    if(matchesTopic(entry.getKey(), routingKey)){
                        matchedQueues.addAll(entry.getValue());
                    }
                }
            }
        }
        return matchedQueues;
    }

    private boolean matchesTopic(String bindingKey, String routingKey) {
        // bindingKey = alert.*, routingKey= alert.severity.critical , not match
        // bindingKey = alert.*, routingKey= alert.critical , match
        // bindingKey = alert.#, routingKey= alert.severity.critical.abc.xyz , match

        String[] bindingParts = bindingKey.split("\\.");
        String[] routingParts = routingKey.split("\\.");
        for (int i = 0; i < bindingParts.length; i++) {
            if(bindingParts[i].equals("*") || bindingParts[i].equals(routingParts[i])) continue;
            if(bindingParts[i].equals("#")) return true;
        }
        return routingParts.length == bindingParts.length;

    }

}
