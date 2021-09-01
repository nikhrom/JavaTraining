package core.threads.lesson25;

import java.util.ArrayList;

public class ThreadDemo {

    public static void main(String[] args) {

        SimpleThread simpleThread = new SimpleThread();

        System.out.println(simpleThread.getState());

        SimpleRunnable simpleRunnable = new SimpleRunnable();

        Thread runnableThread = new Thread(simpleRunnable);
        Thread lambdaThread = new Thread(() -> System.out.println("Hello from lambda" + Thread.currentThread().getName()));



        simpleThread.start();
        System.out.println(simpleThread.getState());

        lambdaThread.start();
        runnableThread.start();


        final var integers = new ArrayList<Integer>();

        integers.add(2);

        try {
            simpleThread.join(100L);
            System.out.println(simpleThread.getState());
            runnableThread.join();
            lambdaThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        simpleThread.interrupt();

        System.out.println(Thread.currentThread().getName());

    }

}
