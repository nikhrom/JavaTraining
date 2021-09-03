package com.github.nikhrom.javatraining.core.threads.lesson25.practice;

import java.util.Queue;

public class ProducerThread implements Runnable{
    private Queue<Integer> list;

    public ProducerThread(Queue<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        synchronized (list){
            while(true){
                int value = 20;
                list.add(value);
                System.out.println("producer adds value " + 20 + ". Size " + list.size());
                try {
                    System.out.println("producer waits: " + 15);
                    list.wait(15);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }


            }


        }
    }
}
