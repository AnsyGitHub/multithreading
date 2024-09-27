package ExecutorService;

import java.util.concurrent.*;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Create a thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit 3 Runnable tasks to the thread pool
        for (int i = 1; i <= 8; i++) {
            final int taskID = i;
            executor.submit(() -> {
                System.out.println("Runnable Task " + taskID + " is running on " + Thread.currentThread().getName());
            });
        }

        // Submit a Callable task to the thread pool
        Callable<Integer> callableTask = () -> {
            System.out.println("Callable Task is running on " + Thread.currentThread().getName());
            return 100;
        };

        Future<Integer> result = executor.submit(callableTask);

        // Retrieve and print the result from the Callable task
        try {
            System.out.println("Callable Task returned: " + result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Shutdown the executor
        executor.shutdown();
    }
}
