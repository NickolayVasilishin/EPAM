package com.epam.javase07.t01.bill;

/**
 * Created by Nick on 08.05.2016.
 */
public class Bill {
    private String id;
    private int balance;

    public Bill(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void withdrawTo(Bill bill, int amount) {
        balance -= amount;
        bill.balance += amount;
    }

    public void withdrawFrom(Bill bill, int amount) {
        withdrawTo(bill, -amount);
    }

    public void commitTransactions() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        balance = 0;
    }
}
