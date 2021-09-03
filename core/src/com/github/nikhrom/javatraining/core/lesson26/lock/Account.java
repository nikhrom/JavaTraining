package com.github.nikhrom.javatraining.core.lesson26.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {



    private final Lock lock = new ReentrantLock();
    private static int counter;
    private int id;
    private int money;


    public Account(int money){
        this.money = money;
        this.id = counter++;
    }

    public void add(int money){
        this.money += money;
    }

    public boolean takeOff(int money){
        if (this.money >= money){
            this.money -= money;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", money=" + money +
                '}';
    }


    public Lock getLock() {
        return lock;
    }
}
