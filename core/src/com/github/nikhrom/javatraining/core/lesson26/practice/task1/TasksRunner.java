package com.github.nikhrom.javatraining.core.lesson26.practice.task1;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class TasksRunner implements Runnable{

    private final BlockingQueue<Task> tasks;

    public TasksRunner(BlockingDeque<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while(true){
            if(!tasks.isEmpty()){
                try {
                    Task takedTask = tasks.take();
                    System.out.println("Я заснул");
                    Thread.sleep(takedTask.getSleepTime() * 1000);
                    System.out.println("Я спал " + takedTask.getSleepTime() + " секунд");
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
