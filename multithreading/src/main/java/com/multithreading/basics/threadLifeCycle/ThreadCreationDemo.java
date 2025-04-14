package com.multithreading.basics.threadLifeCycle;

public class ThreadCreationDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Main Thread Name: "+ Thread.currentThread().getName());
        // Create the thread by passing Runnable interface implementation
        createThreadUsingRunnable();
        // Create the thread by extending Thread class.
        createThreadExtendingThread();
        // create threads by passing customName
        createThreadUsingRunnable("OrderProcessor");
        // create threads by passing customName
        createThreadExtendingThread("ShipmentProcessor");
        Thread.sleep(2000);
        System.out.println("Completed the Excecution");
    }

    static void createThreadUsingRunnable(){
        Thread runnableTask = new Thread(new Task());
        runnableTask.start();
    }
    static void createThreadUsingRunnable(String threadName){
        Thread runnableTask = new Thread(new Task(), threadName);
        runnableTask.start();
    }

    static void createThreadExtendingThread(){
        Thread threadTask = new ThreadTask();
        threadTask.start();
    }

    static void createThreadExtendingThread(String threadName){
        Thread threadTask = new ThreadTask(threadName);
        threadTask.start();
    }
}

class Task implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread name: "+ Thread.currentThread().getName());
    }
}

class ThreadTask extends Thread{
    ThreadTask(){
    }
    ThreadTask(String name){
        super(name);
    }
    @Override
    public void run() {
        System.out.println("Thread name: "+ Thread.currentThread().getName());
    }
}
