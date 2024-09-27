package RunnableInterface;

public class Main {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        Runnable r1= new MyRunnable();

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);
        t1.start();
        t2.start();

        //Modern syntax using Lambdas

        Runnable task = () -> {
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + " working as separate lambda " + i);
            }
        };

        Thread t3 = new Thread(task, " -> Separate Lambda");

        Thread t4 = new Thread(()-> {
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
        ," -> Lambda inside"
        );

        t3.start();
        t4.start();


    }
}
