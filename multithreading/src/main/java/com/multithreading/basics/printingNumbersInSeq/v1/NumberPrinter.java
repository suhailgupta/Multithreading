package com.multithreading.basics.printingNumbersInSeq.v1;

public class NumberPrinter {

    private int startNum;
    private int endNum;

    public NumberPrinter(int startNum, int endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
    }

    public synchronized void printOdd(){
        while(startNum <= endNum){
            while(startNum % 2 == 0){
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

    public synchronized void printEven(){
        while(startNum <= endNum){
            while(startNum % 2 == 1){
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
