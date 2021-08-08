package dmdev.lesson26.latch;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(RocketDetail.values().length);

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        threadPool.submit(new Rocket(countDownLatch));

        Arrays.stream(RocketDetail.values())
                .map(rocketDetail -> new RocketDetailRunnable(rocketDetail, countDownLatch))
                .forEach(threadPool::submit);

        threadPool.shutdown();
        threadPool.awaitTermination(1L, TimeUnit.MINUTES);
    }
}
