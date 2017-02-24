package com.epam.javase07.t03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by Nick on 09.05.2016.
 */
public class SharedResource {
    public final Semaphore semaphore;

    private List<Integer> list;

    public SharedResource() {
        list = new ArrayList<>();
        semaphore = new Semaphore(4);
    }

    public void setElement(Integer element) {
        list.add(element);
    }

    public Integer getELement() {
        if (list.size() > 0) {
            return list.remove(0);
        }
        return null;
    }

}