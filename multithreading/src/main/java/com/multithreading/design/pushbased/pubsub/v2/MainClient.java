package com.multithreading.design.pushbased.pubsub.v2;

import java.util.function.BiConsumer;

public class MainClient {

    public static void main(String[] args) {

        MessageBroker broker = new MessageBroker();

        // create Exchange
        broker.createExchange("logs", ExchangeType.FANOUT);
        broker.createExchange("tasks", ExchangeType.DIRECT);
        broker.createExchange("alerts", ExchangeType.TOPIC);

        // create Queues
        broker.createQueue("logs-queue");
        broker.createQueue("tasks-queue");
        broker.createQueue("alerts-queue");

        // bind queue to exchange
        broker.bindQueueToExchange("logs-queue", "logs", "");
        broker.bindQueueToExchange("tasks-queue", "tasks", "task.urgent");
        broker.bindQueueToExchange("alerts-queue", "alerts", "alert.critical");

        Publisher logPublisher = new Publisher("logs", broker);
        Publisher taskPublisher = new Publisher("tasks", broker);
        Publisher alertPublisher = new Publisher("alerts", broker);


        LogSubscriber logSubscriber = new LogSubscriber(broker);
        TaskSubscriber taskSubscriber = new TaskSubscriber(broker);
        AlertSubscriber alertSubscriber = new AlertSubscriber(broker);

        logPublisher.publish("Info Log","");
        taskPublisher.publish("Urgent Task","task.urgent");
        alertPublisher.publish("Critical System Alert","alert.critical");


        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
