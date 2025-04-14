package com.multithreading.basics.printingNumbersInSeq.v3;

public class NumberPrinter {

    private int startNum;
    private int endNum;
    private int numThreads;

    public NumberPrinter(int startNum, int endNum, int numThreads) {
        this.startNum = startNum;
        this.endNum = endNum;
        this.numThreads = numThreads;
    }

    public synchronized void printNum(int threadId){
        while(true){
            // startNum % 2 == 0 ,1
            // startNum=1,  0%2 = 0, threadId = 0
            //startNum=2,  1%2 = 1, threadId = 1

            while(startNum <= endNum && (startNum-1) % numThreads != threadId){
                try{
                    wait();
                }catch (InterruptedException ex){

                }
            }
            if(startNum > endNum) break;
            System.out.println(Thread.currentThread().getName() + "- "+ startNum);
            startNum++;
            notifyAll();
        }
    }

}
