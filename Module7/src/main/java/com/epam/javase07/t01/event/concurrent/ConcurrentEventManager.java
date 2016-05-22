package com.epam.javase07.t01.event.concurrent;


import com.epam.javase07.t01.event.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Nick on 09.05.2016.
 */
public class ConcurrentEventManager {
    ConcurrentEventProvider provider;
    ExecutorService processors;

    public ConcurrentEventManager(String file) {
        provider = new ConcurrentEventProvider(file, ConcurrentEventProvider.DEFAULT_CAPACITY);
        processors = Executors.newFixedThreadPool(10);
    }

    public void manage() {
        int piece = provider.getSize() / 10;
        while(!provider.isEmpty())
            processors.execute(new EventProcessor(provider.pull(piece)));
        processors.shutdown();
    }

    public static void main(String[] args) {
        new ConcurrentEventManager("bills").manage();
    }
}

