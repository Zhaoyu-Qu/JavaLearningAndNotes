package chapter11multithreading.section2creatingathread;

/**
 * I actually prefer to extend the Thread class because you have direct access to all the Thread methods.
 */

public class ExtendingThread {
    public static void main(String[] args) {
        TimerThread t = new TimerThread(3);
        t.start();
    }
}

class TimerThread extends Thread {
    private int remainingSeconds;
    TimerThread(int remainingSeconds) {
        this.remainingSeconds = remainingSeconds;
    }

    @Override
    public void run() {
        for (int i = remainingSeconds; i > 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Timer interrupted.");
            }
        }
    }
}
