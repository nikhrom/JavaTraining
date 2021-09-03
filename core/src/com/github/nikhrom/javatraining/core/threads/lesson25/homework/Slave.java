package com.github.nikhrom.javatraining.core.threads.lesson25.homework;

import com.github.nikhrom.javatraining.core.threads.lesson25.homework.robot.Robot;
import com.github.nikhrom.javatraining.core.threads.lesson25.homework.robot.RobotPart;

import java.util.*;

public class Slave implements Runnable{

    private final Queue<RobotPart> dump;
    private Deque<RobotPart> forRobots = new LinkedList<>();




    public Slave(Queue<RobotPart> dump) {
        this.dump = dump;
    }

    @Override
    public void run() {
        int count = 0;

        synchronized (dump){
            while (count <= 4 && !dump.isEmpty()){
                count++;
                RobotPart part = dump.remove();
                forRobots.add(part);
                System.out.println(Thread.currentThread().getName() + " подобрал " + part.toString());

                try {
                    int value = RandomUtil.getInt(15);
                    dump.wait(value);
                    System.out.println(Thread.currentThread().getName() + " ждёт " + RandomUtil.getInt(15) + " млс.");
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }

            }
        }
    }

    public List<Robot> getAssembledRobots() {
        Deque<RobotPart> parts = new LinkedList<>(forRobots);
        List<Robot> robots = new LinkedList<Robot>();

        if(parts.isEmpty()) return robots;

        Robot robot = new Robot();
        while (true) {

            for(int i = 0; i < parts.size(); i++) {
                RobotPart removedPart = parts.remove();
                if (!robot.addPart(removedPart)) {
                    parts.add(removedPart);
                }
            }

            if(robot.isAssembled()){
                robots.add(robot);
                robot = new Robot();
            }else{
                return robots;
            }

        }
    }


    public Deque<RobotPart> getForRobots() {
        return forRobots;
    }

}
