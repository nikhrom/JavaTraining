package com.github.nikhrom.javatraining.core.lesson26.semaphore;

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
