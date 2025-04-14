package com.multithreading.basics.threadLifeCycle;

public class ThreadInterruptDemo {

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();

        try {
            Thread.sleep(2000); // Let the thread run for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt(); // Interrupting the thread
        System.out.println("Main thread signaled an interrupt.");
    }
}

class MyThread extends Thread {
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Thread is running...");
                Thread.sleep(1000); // Simulating work
            }
        } catch (InterruptedException e) {
            // clean up the shared memory state
            System.out.println("Thread interrupted! Cleaning up...");
        } finally {
            System.out.println("Thread exiting.");
        }
    }
}
