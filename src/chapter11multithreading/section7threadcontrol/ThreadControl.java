package chapter11multithreading.section7threadcontrol;

import java.util.Scanner;

/**
 * suspend(), resume() and stop() were deprecated by Java 2 and must not be used.
 * suspend() won't relinquish the monitor and could cause deadlocks.
 * resume() was designed to be used in conjunction with suspend() so was deprecated too.
 * stop() relinquishes the monitor immediately which could result in corrupted data structures.
 *
 * The modern way to achieve the same goal is to let run() periodically check an execution status flag.
 */

/**
 * The program keeps counting up from 0 until the user types commands to either suspend, resume or stop it.
 */
public class ThreadControl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Timer timer = new Timer();
        timer.start();

        outerLoop:
        while (true) {
            System.out.println("Enter 'su' to suspend the timer, 'st' to stop.");
            String response = scanner.nextLine();
            if (response.equals("su")) {
                timer.suspendTimer();
                while (true) {
                    System.out.println("Enter 're' to resume, 'st' to stop.");
                    response = scanner.nextLine();
                    if (response.equals("re")) {
                        timer.resumeTimer();
                        break;
                    } else if (response.equals("st")) {
                        break outerLoop;
                    }
                }
            } else if (response.equals("st")) {
                break;
            }
        }
        timer.stopTimer();
        try {
            timer.join();
        } catch (InterruptedException e) {
            System.out.println("Interruption Exception occurred when joining.");
        }

    }
}

class Timer extends Thread {
    private boolean suspendFlag;
    private boolean stopFlag;

    @Override
    public void run() {
        int i = 0;
        while (true) {
            System.out.println(i);
            i++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Timer interrupted");
            }
            //check flags and respond correspondingly
            synchronized (this) {
                while (suspendFlag) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
                if (stopFlag) {
                    break;
                }
            }
        }
    }

    synchronized void suspendTimer() {
        suspendFlag = true;
        stopFlag = false;
    }

    synchronized void stopTimer() {
        stopFlag = true;
        suspendFlag = false;
        notify();
    }

    synchronized void resumeTimer() {
        suspendFlag = false;
        stopFlag = false;
        notify();
    }
}