package com.multithreading.basics.synchronizedDemo;

public class DepositThread extends Thread{
    private final BankAccount account;
    private final int amount;

    public DepositThread(BankAccount account, int amount, String name) {
        super(name);
        this.account = account;
        this.amount = amount;
    }

    public void run() {
        account.deposit(amount, getName());
    }
}
