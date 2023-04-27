package chapter11multithreading.section4synchronization;

public class Kettle extends Thread {
    Bottle bottle;

    Kettle(Bottle bottle) {
        this.bottle = bottle;
    }

    @Override
    public void run() {
        bottle.fill();
    }
}
