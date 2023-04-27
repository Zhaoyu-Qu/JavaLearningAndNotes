package chapter11multithreading.section4synchronization;

/**
 * Synchronized methods are marked by the keyword 'synchronized' and are embedded in the shared object.
 * However, there are times when you can't extend or modify a non-synchronized class yet still want to
 * access it in a synchronized way. This can be achieved using synchronized statements.
 */
public class SynchronizedStatement {
    public static void main(String[] args) {
        Bottle bottle = new Bottle();
        SyncedKettle[] kettles = new SyncedKettle[2];
        for (Kettle kettle : kettles) {
            kettle = new SyncedKettle(bottle);
            kettle.start();
        }
    }
}

class SyncedKettle extends Kettle {
    SyncedKettle(Bottle bottle) {
        super(bottle);
    }

    @Override
    public void run() {
        //The calling thread will enter the monitor of the bottle object
        //Other instances of the SyncedKettle will stop here and be sent to the waiting list, waiting on the monitor
        //until whoever has the monitor releases it.
        synchronized (bottle) {
            bottle.fill();
        }
    }
}