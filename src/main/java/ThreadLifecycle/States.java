package ThreadLifecycle;

public class States {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Thread State: " + Thread.currentThread().getState() + " - Running");
                Thread.sleep(1000);
                System.out.println("Thread is waking up from sleep.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // New state
        System.out.println("Thread State: " + thread.getState() + " - New");

        thread.start();

        // Check after starting the thread
        System.out.println("Thread State: " + thread.getState() + " - Runnable (or Running)");

        // Simulating waiting for the thread to block
        Thread.sleep(500); // This gives the thread time to enter "sleep" (timed waiting)

        // Blocked/Waiting state
        System.out.println("Thread State: " + thread.getState() + thread.getName() + " - Timed Waiting");

        // Wait for the thread to finish
        thread.join();

        // Terminated state
        System.out.println("Thread State: " + thread.getState() + Thread.currentThread().getName() +  " - Terminated");

    }


}
