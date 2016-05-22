package com.epam.javase07.t01.event;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nick on 09.05.2016.
 */
public class EventManager {
    EventProvider provider;
    List<EventProcessor> processors;

    public EventManager(String file) {
        provider = new EventProvider(file, EventProvider.DEFAULT_CAPACITY);
        processors = new LinkedList<>();
    }

    public void manage() throws InterruptedException {
        int piece = provider.getSize() / processors.size();
//        for(EventProcessor p:processors)
        while(!provider.isEmpty()) {
            EventProcessor p = new EventProcessor(provider.pull(piece));
            processors.add(p);
            p.start();
        }
        for(EventProcessor p:processors)
            p.join();
    }

    public static void main(String[] args) throws InterruptedException {
        new EventManager("bills").manage();
    }
}
