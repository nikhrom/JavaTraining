package com.github.nikhrom.javatraining.core.threads.lesson25.queue;

public class Cashbox {



    private static int counter = 0;
    private int id;

    public Cashbox(){
        id = counter++;
    }

    @Override
    public String toString() {
        return "Cashbox{" +
                "id=" + id +
                '}';
    }

}
