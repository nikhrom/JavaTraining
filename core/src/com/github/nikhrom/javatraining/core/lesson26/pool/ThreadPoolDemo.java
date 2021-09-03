package com.github.nikhrom.javatraining.core.lesson26.pool;

import java.util.concurrent.*;

public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        Future<Integer> future = threadPool.submit(() -> {
            Thread.sleep(5000L);
            System.out.println("it's collable");
            return 1;
        });
        System.out.println("future: " + future.get());

        threadPool.shutdown();

    }
}
