package com.multithreading.basics.interThreadComm;

public class SharedResource {
    
    private Integer data = null;
    public synchronized void produce() {
        System.out.println("Producer: Producing data...");
        data = 10;
        System.out.println("Producer: Produced data..." + data);
        notify(); // Notify waiting thread
    }

    public synchronized void consume() {
        while (data == null) {
            try {
                System.out.println("Consumer: Waiting for data...");
                wait(); // Release lock and wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Consumer: Consuming data..."+ data);
    }
}
