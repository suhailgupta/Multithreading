package com.multithreading.basics.synchronizedDemo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankAccount {
    private int balance = 1000;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public synchronized void withdraw(int amount, String threadName) {
        if (balance >= amount) {  // Check balance
            System.out.println(threadName + " [ " + getTimestamp() + " ] is withdrawing " + amount);
            /*try {
                Thread.sleep(100); // Simulate delay
            } catch (InterruptedException e) {

            }*/
            balance -= amount;  // Deduct amount
            System.out.println(threadName + " [ " + getTimestamp() + " ] completed withdrawal | Balance: " + balance);
        } else {
            System.out.println(threadName + " [ " + getTimestamp() + " ] attempted to withdraw " + amount + " but insufficient balance.");
        }
    }

    public synchronized void deposit(int amount, String threadName) {
        System.out.println(threadName + " [ " + getTimestamp() + " ] is depositing " + amount);
        /*try {
            Thread.sleep(100); // Simulate delay
        } catch (InterruptedException e) {

        }*/
        balance += amount;  // Update balance
        System.out.println(threadName + " [ " + getTimestamp() + " ] deposited " + amount + " | New Balance: " + balance);
    }

    public int getBalance(){
        return balance;
    }

    private String getTimestamp() {
        return LocalDateTime.now().format(formatter);
    }
}
