package com.multithreading.basics.synchronizedDemo;

public class WithdrawThread extends Thread{
    private final BankAccount account;
    private final int amount;

    public WithdrawThread(BankAccount account, int amount, String name) {
        super(name);
        this.account = account;
        this.amount = amount;
    }

    public void run() {
        account.withdraw(amount, getName());
    }
}
