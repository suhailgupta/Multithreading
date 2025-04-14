package com.multithreading.basics.printingNumbersInSeq.v3;

public class MainClient {

    public static void main(String[] args) {
        int numThreads = 5;
        NumberPrinter numberPrinter = new NumberPrinter(1, 20, numThreads);
        for (int i = 0; i < numThreads; i++) {
            final int threadId = i;
            Thread thread = new Thread(() -> numberPrinter.printNum(threadId));
            thread.start();
        }

    }
}
