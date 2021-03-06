package com.epam.javase07.t01.event.concurrent;

import com.epam.javase07.t01.bill.Bill;
import com.epam.javase07.t01.event.Event;
import com.epam.javase07.t01.event.EventProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Stream;

/**
 * Created by Nick on 09.05.2016.
 */
public class ConcurrentEventProvider {
    private String file;
    private final Queue<Event> queue;
    private final int capacity;
    public static final int DEFAULT_CAPACITY = 500;
    private int currentLines;
    private volatile boolean needToUpdate;

    public ConcurrentEventProvider(String file, int capacity) {
        this.file = file;
        this.capacity = capacity;
        queue = new ConcurrentLinkedQueue<>();
        load();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int getSize() {
        return queue.size();
    }

    private void addEvent(String line) {
        queue.add(new Event(
                new Bill(line.split("_")[0]),
                new Bill(line.split("_")[1]),
                Integer.valueOf(line.split("_")[2])));
    }

    private ConcurrentEventProvider load() {
            try (Stream<String> lines = Files.lines(Paths.get(file))) {
                lines.skip(currentLines).limit(capacity).forEach(this::addEvent);
                currentLines += queue.size();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return this;
    }

    public List<Event> pull(int number) {
        if(needToUpdate)
            load();
        LinkedList<Event> events = new LinkedList<>();
            for (int i = 0; i < number && !queue.isEmpty(); i++) {
                events.add(queue.poll());
                if (queue.size() < capacity / 4)
                    needToUpdate = true;
            }
        return events;
    }


}
