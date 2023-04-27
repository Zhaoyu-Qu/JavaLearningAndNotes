package chapter11multithreading.section3joiningthreads;
/**
 * The referee (main thread) will only start the game when both players(threads) are ready (finished)
 */

import java.util.Random;

public class JoiningThreads {
    public static void main(String[] args) {
        Player[] players = new Player[2];
        players[0] = new Player("Jack");
        players[1] = new Player("Sam");

        for (Player p : players) {
            p.start();
        }

        //check if threads are alive
        for (Player p : players) {
            if (p.isAlive()) {
                System.out.println(p + " is not ready yet. The referee will wait.");
            } else {
                System.out.println(p + " is ready. If everyone is ready the game will start now.");
            }
        }

        //if the child thread is alive, the main thread will enter the WAITING state
        //until the child thread finishes.
        for (Player p : players) {
            try {
                p.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted when joining.");
            }
        }

        //At this point, both threads must have finished because the main thread invoked join on them.
        System.out.println("Both players are ready. The game will start now.");
    }
}

class Player extends Thread {
    private String playerName;

    Player(String name) {
        super(name);
        playerName = name;
    }

    @Override
    public void run() {
        int remainingSeconds = new Random().nextInt(5);
        for (int i = remainingSeconds; i > 0; i--) {
            System.out.printf(playerName + " will be ready in %d seconds.\n", i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(playerName + " interrupted.");
            }
        }
        System.out.println(playerName + " is ready to go.");
    }

    @Override
    public String toString() {
        return playerName;
    }
}