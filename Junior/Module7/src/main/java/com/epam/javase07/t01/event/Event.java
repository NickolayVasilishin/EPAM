package com.epam.javase07.t01.event;

import com.epam.javase07.t01.bill.Bill;

/**
 * Created by Nick on 08.05.2016.
 */
public class Event {
    private Bill sender;
    private Bill receiver;
    private int amount;

    public Event(Bill firstBill, Bill secondBill, int amount) {
        this.sender = firstBill;
        this.receiver = secondBill;
        this.amount = amount;
    }

    public Bill getReceiver() {
        return receiver;
    }

    public Bill getSender() {
        return sender;
    }

    public int getAmount() {
        return amount;
    }
}
