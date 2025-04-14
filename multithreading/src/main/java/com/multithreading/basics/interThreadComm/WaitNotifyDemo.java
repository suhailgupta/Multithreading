package com.multithreading.basics.interThreadComm;



public class WaitNotifyDemo {

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Runnable submittingJobTask = () -> {
            try{
                Thread.sleep(1000);
            }catch (InterruptedException ex){
                // handle exception.
            }
            resource.produce();
        };

        Runnable printTask = resource::consume;
        Thread prinThread = new Thread(printTask);
        Thread submittingJobThread = new Thread(submittingJobTask);
        prinThread.start();
        submittingJobThread.start();
    }
}

