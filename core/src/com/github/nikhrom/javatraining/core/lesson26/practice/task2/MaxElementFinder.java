package com.github.nikhrom.javatraining.core.lesson26.practice.task2;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MaxElementFinder {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Random random = new Random();
        int length = random.nextInt(1_000_000);
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++)
            array[i] = random.nextInt(300) + 1;

//        ExecutorService singleThread = Executors.newSingleThreadExecutor();
        ExecutorService tenThreads = Executors.newFixedThreadPool(10);

//        Future<OptionalInt> maxInSingleThread = singleThread.submit(() -> Arrays.stream(array)
//                .max());


        System.out.println(Arrays.toString(array));

        ArrayList<Future<OptionalInt>> maxElements = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            long skipElements = array.length / 10 * i;
            maxElements.add(tenThreads.submit(() -> Arrays.stream(array)
                    .skip(skipElements)
                    .max()));
        }

        Optional<Future<OptionalInt>> max = maxElements.stream().max((v1, v2) -> {
            try {
                return Integer.compare(v1.get().getAsInt(), v2.get().getAsInt());
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return 0;
        });


        max.ifPresent((future) -> {
            try {
                System.out.println(future.get().getAsInt());
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

    }

}
