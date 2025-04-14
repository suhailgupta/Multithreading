package com.multithreading.basics.printingNumbersInSeq.v2;

public class NumberPrinter {

    private int startNum;
    private int endNum;

    public NumberPrinter(int startNum, int endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
    }

    public synchronized void printNum(int threadId){
        while(startNum <= endNum){
            // startNum % 2 == 0 ,1
            // startNum=1,  0%2 = 0, threadId = 0
            //startNum=2,  1%2 = 1, threadId = 1

            while((startNum-1) % 2 != threadId){
                try{
                    wait();
                }catch (InterruptedException ex){

                }
            }
            if(startNum > endNum) break;
            System.out.println(Thread.currentThread().getName() + "- "+ startNum);
            startNum++;
            notify();
        }
    }

}
