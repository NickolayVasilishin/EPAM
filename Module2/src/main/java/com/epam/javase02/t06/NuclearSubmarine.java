package com.epam.javase02.t06;

/**
 * @author Nick
 * @version 0.1
 * Simple model of a nuclear submarine.
 */
public class NuclearSubmarine {
    private Engine engine;

    public NuclearSubmarine() {
        engine = new Engine().start();
    }

    /**
     *
     * @param destination - coordinates or name, where submarine should to go
     */
    public void moveTo(String destination) {
        System.out.println("Moving to " + destination);
        engine.accelerate();
    }

    private class Engine {
        private volatile boolean going = false;
        public Engine start(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        if(going) {
                            System.out.println("TRRRR");
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            System.out.println("Tr tr tr");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
            return this;
        }

        public void accelerate() {
            going = true;
        }
    }
}
