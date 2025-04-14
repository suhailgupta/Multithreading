package com.multithreading.basics.printingNumbersInSeq.v1;

public class MainClient {

    public static void main(String[] args) {
        NumberPrinter numberPrinter = new NumberPrinter(1, 20);
        Thread oddThread = new Thread(() -> numberPrinter.printOdd());
        Thread evenThread = new Thread(() -> numberPrinter.printEven());
        oddThread.start();
        evenThread.start();
    }
}
