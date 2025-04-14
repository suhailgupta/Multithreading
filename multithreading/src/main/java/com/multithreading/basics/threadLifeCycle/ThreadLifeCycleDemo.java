package com.multithreading.basics.threadLifeCycle;

public class ThreadLifeCycleDemo {

    public static void main(String[] args) throws InterruptedException{
        ThreadLifecycle thread = new ThreadLifecycle();

        System.out.println(thread.getName() + " - State:" + thread.getState());
        thread.start(); // Moves to RUNNABLE state
        Thread.sleep(100); // Small delay to ensure state change is visible
        System.out.println(thread.getName() + " - State after start: " + thread.getState());

        Thread.sleep(4000); // Wait to allow the thread to enter TIMED_WAITING
        System.out.println(thread.getName() + " - State after sleep: " + thread.getState());

        // withdraw
        synchronized (thread) {
            thread.notify(); // Wakes up the thread from WAITING
        }

        Thread.sleep(100); // Small delay to allow state change
        System.out.println(thread.getName() + " - Final State: " + thread.getState());
    }

}

class ThreadLifecycle extends Thread {
    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " - State: RUNNING");
        try {
            Thread.sleep(3000); // Moves to TIMED_WAITING state
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " - Interrupted");
        }

        // deposit
        synchronized (this) {
            try {
                wait(); // Moves to WAITING state
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " - Interrupted from wait");
            }
        }
    }
}
