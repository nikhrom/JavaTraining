package com.github.nikhrom.javatraining.core.threads.lesson25;

public class SimpleRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Hello from runnable(): " + Thread.currentThread().getName());
    }
}