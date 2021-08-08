package dmdev.lesson26.dmdevpractice;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Task2 {

    public static void main(String[] args) {
        int[] values = new int[100_000_000];
        Random random = new Random();
        for (int i = 0; i < values.length; i++){
            values[i] = random.nextInt(300) + 1;
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        trackTime(() -> findMax(values));
        trackTime(() -> {
            try {
                return findMaxParallel(values, threadPool);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return Integer.MIN_VALUE;
        });


        threadPool.shutdownNow();
    }

    private static int trackTime(Supplier<Integer> task){
        long startTime = System.currentTimeMillis();
        int result = task.get();
        System.out.println(result + ": " + (System.currentTimeMillis() - startTime));
        return result;
    }


    private static int findMax(int[] values){
        return Arrays.stream(values)
                .max()
                .orElse(Integer.MIN_VALUE);
    }

    private static int findMaxParallel(int[] values, ExecutorService executor) throws ExecutionException, InterruptedException {
        return executor.submit(() -> Arrays.stream(values)
                .parallel()
                .max()
                .orElse(Integer.MIN_VALUE)).get();
    }


}
