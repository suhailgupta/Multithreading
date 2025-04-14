package com.multithreading.basics.synchronizedDemo;

public class RaceConditionDemo {

    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        WithdrawThread w1 = new WithdrawThread(account, 200, "WithdrawThread");
        DepositThread d1 = new DepositThread(account, 1000, "DepositThread");

        w1.start();
        d1.start();
        try{
            d1.join();
            w1.join();
        }catch (InterruptedException ex){

        }
        System.out.println("Operation completed. Bank Balance- "+ account.getBalance());
    }
}
