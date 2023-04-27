package chapter11multithreading.section1introduction;

/**
 * The main thread can be referenced by calling the method currentThread()
 */
public class MainThreadDemo {
    public static void main(String[] args) {
        //t is a reference to the current thread, i.e. the main thread
        Thread t = Thread.currentThread();

        //From left to right: thread's identifier, name, priority, and the name of the thread group
        //The default priority is 5
        //A thread group is a data structure that controls the state of a collection of threads as a whole
        System.out.println("\n" + t);
        t.setName("My Thread");
        System.out.println(t + "\n");

        //The static method Thread.sleep() throws an exception when interrupted by another thread
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println(i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
    }
}
