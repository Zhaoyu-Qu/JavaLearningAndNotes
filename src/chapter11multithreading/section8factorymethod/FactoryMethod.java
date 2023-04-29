package chapter11multithreading.section8factorymethod;

import java.util.Random;

public class FactoryMethod {
    public static void main(String[] args) {
        RandomTimer[] timers = new RandomTimer[3];
        for (int i = 0; i < timers.length; i++) {
            timers[i] = RandomTimer.createAndStart();
        }
    }
}

class RandomTimer extends Thread {
    //A factory method to replace the constructor
    //It sometimes streamlines the code and its execution.
    static RandomTimer createAndStart() {
        RandomTimer r = new RandomTimer();
        r.start();
        return r;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        for (int i = new Random().nextInt(1, 5); i > -1; i--) {
            System.out.printf(threadName + ": %d\n", i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Timer interrupted.");
            }
        }
    }
}