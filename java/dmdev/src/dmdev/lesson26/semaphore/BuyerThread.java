package dmdev.lesson26.semaphore;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class BuyerThread implements Runnable{

    private final Semaphore cashboxes;

    public BuyerThread(Semaphore cashboxes) {
        this.cashboxes = cashboxes;
    }

    @Override
    public void run() {
        try {
            cashboxes.acquire();
            System.out.println(Thread.currentThread().getName() + " обслуживается в какой-то кассе");
            Thread.sleep(5L);
            System.out.println(Thread.currentThread().getName() + " освобождает какую-то кассу ");
            cashboxes.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

