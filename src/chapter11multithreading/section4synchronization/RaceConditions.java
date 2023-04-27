package chapter11multithreading.section4synchronization;

/**
 * Both kettles are trying to fill up a bottle. They have carefully checked that the bottle is empty
 * before filling. Unfortunately, an interrupt happens right after the checking but before the filling
 * so they both think the bottle is empty and end up overfilling the bottle.
 */
public class RaceConditions {
    public static void main(String[] args) {
        Bottle bottle = new Bottle();
        Kettle[] kettles = new Kettle[2];
        for (Kettle kettle : kettles) {
            kettle = new Kettle(bottle);
            kettle.start();
        }
    }
}