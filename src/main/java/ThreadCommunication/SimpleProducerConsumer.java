package ThreadCommunication;

class SharedResource {
    private int data;
    private boolean available = false;

    public synchronized void produce(int value) throws InterruptedException {
        while (available) wait(); // Wait if data is already produced
        data = value; // Produce data
        available = true; // Mark data as available
        notify(); // Notify consumer
    }

    public synchronized int consume() throws InterruptedException {
        while (!available) wait(); // Wait if no data is available
        available = false; // Mark data as consumed
        notify(); // Notify producer
        return data; // Return consumed data
    }
}

class Producer extends Thread {
    private final SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                resource.produce(i);
                System.out.println("Produced: " + i);
                Thread.sleep(100); // Simulate production time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer extends Thread {
    private final SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                int value = resource.consume();
                System.out.println("Consumed: " + value);
                Thread.sleep(150); // Simulate consumption time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class SimpleProducerConsumer {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        new Producer(resource).start();
        new Consumer(resource).start();
    }
}
