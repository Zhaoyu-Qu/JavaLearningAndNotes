package chapter11multithreading.section4synchronization;

/**
 * Synchronized methods are defined with the keyword 'synchronized'.
 * Once a thread has entered a synchronized method, no other thread can enter any other
 * synchronized method on the same instance. Although non-synchronized methods will remain callable.
 */
public class SynchronizedMethod {
    public static void main(String[] args) {
        SyncedBottle bottle = new SyncedBottle();
        Kettle[] kettles = new Kettle[2];
        for (Kettle kettle : kettles) {
            kettle = new Kettle(bottle);
            kettle.start();
        }
    }
}

class SyncedBottle extends Bottle {
    @Override
    synchronized public void fill() {
        super.fill();
    }
}