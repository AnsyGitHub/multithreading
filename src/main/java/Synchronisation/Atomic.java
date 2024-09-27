package Synchronisation;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        // Create multiple threads that increment the counter
        Thread t1 = new Thread(() -> incrementCounter());
        Thread t2 = new Thread(() -> incrementCounter());

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // The final value of counter will be consistent
        System.out.println("Final Counter Value: " + counter.get());
    }

    private static void incrementCounter() {
        for (int i = 0; i < 20000; i++) {
            counter.incrementAndGet();  // Atomic increment
        }
    }
}
