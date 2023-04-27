package chapter11multithreading.section4synchronization;

/**
 * This class demonstrates a guaranteed race condition.
 * When two or more kettles try to fill the bottle at the same time, they will always overfill it because of
 * a race condition, even if they have previously checked that the bottle was empty.
 */
public class Bottle {
    int waterLevel;

    Bottle() {
        waterLevel = 0;
    }

    public void fill() {
        if (isEmpty()) {
            //The current thread is sent to the waiting list after checking the water level
            //but before filling the bottle. This guarantees a racing condition.
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

    boolean isEmpty() {
        return waterLevel == 0;
    }

    boolean isFull() {
        return waterLevel == 1;
    }

    boolean isOverflowing() {
        return waterLevel > 1;
    }
}
