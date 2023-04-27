package chapter11multithreading.section2creatingathread;

/**
 * It's good practice to instantiate an object of Thread from within the class.
 */

public class ImplementingRunnable {
    public static void main(String[] args) {
        TimerRunnable timer = new TimerRunnable(3);
        timer.t.start();
    }
}

class TimerRunnable implements Runnable {
    Thread t;
    private int remainingSeconds;

    TimerRunnable(int remainingSeconds) {
        this.remainingSeconds = remainingSeconds;
        t = new Thread(this);
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