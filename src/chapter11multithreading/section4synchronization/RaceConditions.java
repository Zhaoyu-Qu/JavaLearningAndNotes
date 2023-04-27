package chapter11multithreading.section4synchronization;

/**
 * Both kettles are trying to fill up a bottle. They have carefully checked that the bottle is empty
 * before filling. Unfortunately, an interrupt happens right after the checking but before the filling
 * so they both think the bottle is empty and end up overfilling the bottle.
 */
public class RacingConditions {
    public static void main(String[] args) {
        BottleWithGuaranteedRacingConditions bottle = new BottleWithGuaranteedRacingConditions();
        Kettle[] kettles = new Kettle[2];
        for (Kettle kettle : kettles) {
            kettle = new Kettle(bottle);
            kettle.start();
        }
    }
}

class BottleWithGuaranteedRacingConditions extends Bottle {
    @Override
    public void fill() {
        if (isEmpty()) {
            //The current thread is sent to the waiting list after checking the water level
            //but before filling the bottle. This guarantees a racing condition
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Filling action interrupted.");
            }
            waterLevel++;
        } else if (isFull()) {
            System.out.println("The bottle is full.");
        }
        if (isOverflowing()) {
            System.out.println("Crap, I overfilled the bottle and it spilled over.");
            System.out.println("How did that happen? I swear I checked before filling and it was empty!");
        }
    }
}