package ThreadCommunication;

class ThreadA {
    public static void main(String[] args) {
        ThreadB b = new ThreadB(); // Create an instance of ThreadB
        b.start(); // Start ThreadB

        synchronized(b) { // Synchronize on the ThreadB instance
            try {
                System.out.println("Waiting for b to complete...");
                b.wait(); // Wait for ThreadB to complete
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Total is: " + b.total); // Print the total calculated by ThreadB
        }
    }
}

class ThreadB extends Thread {
    int total; // Variable to store the total

    public void run() {
        synchronized(this) { // Synchronize on the current instance of ThreadB
            for(int i = 0; i < 100; i++) {
                total += i; // Calculate the sum from 0 to 99
            }
            notify(); // Notify the waiting thread (ThreadA)
        }
    }
}
