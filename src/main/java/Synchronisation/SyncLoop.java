package Synchronisation;

public class SyncLoop {
    private static int counter = 0;

    static class MyRunnable implements Runnable {
        private final String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + ": begin");
            for (int i = 0; i < 20000; i++) {
                incrementCounter();
            }
            System.out.println(name + ": done");
        }
    }

    private synchronized static void incrementCounter() {
        counter = counter + 1;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println("main: begin (counter = " + counter + ")");
        Thread t1 = new Thread(new MyRunnable("A"));
        Thread t2 = new Thread(new MyRunnable("B"));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("main: done with both (counter = " + counter + ")");
        System.out.println("Execution time (with synchronization): " + (endTime - startTime) + " ms");
    }
}
