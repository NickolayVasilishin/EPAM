package com.epam.javase07.t01.event;

import com.epam.javase07.t01.bill.Bill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nick on 08.05.2016.
 */
public class EventProcessor extends Thread {
    private final List<Event> events;
    private Map<String, Bill> bills;

    public EventProcessor(List<Event> events) {
        this.events = events;
        bills = new HashMap<>();
    }

    @Override
    public void run() {
        events.parallelStream().forEach(event -> {
            event.getSender().withdrawTo(event.getReceiver(), event.getAmount());
            //there must be some accumulative logic
            bills.put(event.getSender().getId(), event.getSender());
            bills.put(event.getReceiver().getId(), event.getReceiver());
        });
        bills.forEach((s, bill) -> bill.commitTransactions());
        System.out.println("Events processed: " + events.size());
    }
}
