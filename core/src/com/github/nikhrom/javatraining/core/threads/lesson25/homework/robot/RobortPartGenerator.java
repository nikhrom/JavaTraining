package com.github.nikhrom.javatraining.core.threads.lesson25.homework.robot;

import java.util.*;

public class RobortPartGenerator {

    static final Random RANDOM_PART = new Random();

    private RobortPartGenerator(){};

    public static Deque<RobotPart> getRobotParts(int counter){
        Deque<RobotPart> parts = new LinkedList<>();
        for(int i = 0; i < counter; i++)
            parts.add(getRobotPart());
        return parts;
    };

    public static RobotPart getRobotPart(){
        int random = RANDOM_PART.nextInt(9);

        switch (random){
            case 0: return new Body();
            case 1: return new Cpu();
            case 2: return new Hdd();
            case 3: return new Head();
            case 4: return new LeftHand();
            case 5: return new LeftLeg();
            case 6: return new Ram();
            case 7: return new RightHand();
            case 8: return new RightLeg();

        }

        return null;
    }
}
