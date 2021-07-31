package dmdev.threads.lesson25.practice;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PracticeDemo {

    public static void main(String[] args) throws InterruptedException {
        Queue<Integer> list = new LinkedList<>();

        Thread producer = new Thread(new ProducerThread(list));
        Thread consumer = new Thread(new ConsumerThread(list));

        producer.start();
        consumer.start();

        producer.join();
        producer.join();


    }

}
