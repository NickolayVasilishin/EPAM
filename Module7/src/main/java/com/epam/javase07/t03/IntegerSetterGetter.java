package com.epam.javase07.t03;

import java.util.Random;

/**
 * Created by Nick on 09.05.2016.
 */
public class IntegerSetterGetter extends Thread {
    private SharedResource resource;
    private boolean run;
    private Random rand = new Random();

    public IntegerSetterGetter(String name, SharedResource resource) {
        super(name);
        this.resource = resource;
        run = true;
    }

    public void stopThread() {
        run = false;
    }

    public void run() {
        int action;

        try {
            while (run) {
                action = rand.nextInt(1000);
                if (action % 2 == 0) {
                    getIntegersFromResource();
                } else {
                    setIntegersIntoResource();
                }
            }
            System.out.println("Поток " + getName() + " завершил работу.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getIntegersFromResource() throws InterruptedException {
        Integer number;
//        synchronized (resource) {
        if(resource.semaphore.tryAcquire()) {
            System.out.println("Поток " + getName() + " хочет извлечь число.");
            number = resource.getELement();
            while (number == null) {
                System.out.println("Поток " + getName() + " ждет пока очередь заполнится.");
                resource.wait(100);
                System.out.println("Поток " + getName() + " возобновил работу");
                if((number = resource.getELement()) == null)
                    return;
            }
            System.out.println("Поток " + getName() + " извлек число " + number);
        }
    }

    private void setIntegersIntoResource() throws InterruptedException {
        Integer number = rand.nextInt(500);
        synchronized (resource) {
            resource.setElement(number);
            System.out.println("Поток " + getName() + " записал число " + number);
            resource.notify();
        }

    }
}

