package dmdev.threads.lesson25.queue;

import java.util.Queue;

public class BuyerThread implements Runnable{

    private final Queue<Cashbox> cashboxes;

    public BuyerThread(Queue<Cashbox> cashboxes){
        this.cashboxes = cashboxes;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (!cashboxes.isEmpty()) {
                    Cashbox cashbox = cashboxes.remove();
                    System.out.println(Thread.currentThread().getName() + " обслуживается в кассе " + cashbox);

                    Thread.sleep(5L);

                    System.out.println(Thread.currentThread().getName() + " освобождает кассу " + cashbox);
                    cashboxes.add(cashbox);

                    break;
                } else {
                    System.out.println(Thread.currentThread().getName() + " ожидает свободную кассу ");
                    Thread.sleep(5L);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

