package chapter11multithreading.section6deadlock;

/**
 * Two friends call each other at the sametime. Both are waiting for the other side to become available.
 * This results in a deadlock.
 */
public class DeadLock {
    public static void main(String[] args) throws Exception {
        Friend jason = new Friend("Jason");
        Friend lucy = new Friend("Lucy");
        jason.addContact(lucy);
        lucy.addContact(jason);
        jason.start();
        lucy.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("2 years has passed. The two friends never managed to get hold of each other.");
        System.out.println("They have both been waiting for the other side to become available.");
        System.exit(0);
    }
}

class Friend extends Thread {
    String name;

    Friend(String name) {
        this.name = name;
    }

    Friend contact;

    void addContact(Friend contact) {
        this.contact = contact;
    }

    synchronized void call() {
        System.out.printf("I'm calling %s. If she/he is busy, I'll wait.\n", contact.name);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        contact.answer();
        System.out.printf("Hey %s, it's me, %s!\n", contact.name, name);
    }

    synchronized void answer() {
        System.out.printf("Hi there, this is %s.\n", name);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        call();
    }
}