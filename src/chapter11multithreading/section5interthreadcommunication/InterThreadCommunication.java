package chapter11multithreading.section5interthreadcommunication;

/**
 * In this example, the producer puts items into the queue, and the consumer takes items from the queue.
 * The queue can only accommodate one item, so the consumer and producer will notify each other once they
 * have done their job.
 * <p>
 * Initially, I thought I could get away with just using synchronized methods. For example, if the producer wanted to
 * put an item into the queue while the queue had already been full, it could just give up the monitor
 * and exit the method. This doesn't work, because every item is unique, meaning every get or put method call must
 * be followed through. If you exit the method, then you will lose the unique item you were going to get/put.
 * <p>
 * The solution is to use wait(), notify() and notifyAll().
 * The wait() method causes the caller to willingly give up the monitor but not exit the method.
 * The notify() method will randomly pick a waiting thread to wake up. The awakened thread will resume
 * whatever it was working on before.
 * The notifyAll() method will put all waiting threads into the ready state.
 */
public class InterThreadCommunication {
    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        Consumer c = new Consumer(q);
        Producer p = new Producer(q);
        p.start();
        c.start();
        try {
            p.join();
            c.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class MyQueue {
    Integer n;

    MyQueue() {
        n = null;
    }

    synchronized Integer get() {
        while (n == null) { //use while instead of if in case a spurious wakeup happens
            System.out.println("Consumer going to sleep.");
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        Integer returnValue = n;
        n = null;
        System.out.println("Got " + returnValue);
        notify();
        return returnValue;
    }

    synchronized void put(Integer n) {
        while (this.n != null) {
            System.out.println("Producer going to sleep.");
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        this.n = n;
        System.out.println("Put " + n);
        notify();
    }
}

class Producer extends Thread {
    MyQueue q;

    Producer(MyQueue q) {
        this.q = q;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            q.put(i);
        }
        q.put(-1);
    }
}

class Consumer extends Thread {
    MyQueue q;

    Consumer(MyQueue q) {
        this.q = q;
    }

    @Override
    public void run() {
        while (q.get() != -1) ;
    }
}