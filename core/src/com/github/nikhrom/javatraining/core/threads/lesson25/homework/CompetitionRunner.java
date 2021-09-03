/*Двое безумных учёных устроили соревнование, кто из них
соберёт больше роботов за 100 ночей.
Для этого каждую ночь каждый из них отправляет своего
прислужника на свалку фабрики роботов за деталями.
Чтобы собрать одного робота им нужно:
Голова, Тело, Левая рука, Правая рука, Левая нога, Правая
нога, CPU, RAM, HDD
В первую ночь на свалке находится 20 случайных деталей.
Каждую ночь фабрика выбрасывает на свалку от 1 до 4
случайных деталей.
В то же самое время прислужники обоих учёных
отправляются на свалку, и каждый собирает от 1 до 4
случайных деталей. Если на свалке деталей нет –
прислужник уходит ни с чем.
Затем они возвращаются домой и отдают детали хозяевам.
Учёные пытаются собрать как можно больше роботов из
деталей, которые они получили.
Написать программу, симулирующую этот процесс. Для
симуляции принять, что каждая ночь наступает через 100мс.
Фабрика и два прислужника действуют в одно и то же
время.
После 100 ночей (около 10 секунд) определить победителя
соревнования.*/


package com.github.nikhrom.javatraining.core.threads.lesson25.homework;

import com.github.nikhrom.javatraining.core.threads.lesson25.homework.robot.RobortPartGenerator;
import com.github.nikhrom.javatraining.core.threads.lesson25.homework.robot.RobotPart;

import java.util.Deque;


public class CompetitionRunner {

    public static void main(String[] args) throws InterruptedException {
        Deque<RobotPart> dump = RobortPartGenerator.getRobotParts(20);

        Slave slave1 = new Slave(dump);
        Slave slave2 = new Slave(dump);


        for(int i = 0; i < 100; i++){
            Thread threadSlave1 = new Thread(slave1);
            threadSlave1.setName("slave1");
            Thread threadSlave2 = new Thread(slave2);
            threadSlave2.setName("slave2");

            threadSlave1.start();
            threadSlave2.start();

            threadSlave1.join();
            threadSlave2.join();


            dump.addAll(RobortPartGenerator.getRobotParts(RandomUtil.getInt(10)));
            System.out.println("НАСТУПИЛА НОЧЬ");
            Thread.sleep(100L);
        }

        int slave1Count =  slave1.getAssembledRobots().size();
        int slave2Count =  slave2.getAssembledRobots().size();

        System.out.println(slave1.getForRobots());
        System.out.println(slave2.getForRobots());

        System.out.println("У первого: " + slave1Count + ". У второго: " + slave2Count);

        if(slave1Count > slave2Count){
            System.out.println("Победил первый учёный");
        }else if (slave2Count > slave1Count){
            System.out.println("Победил второй учёный");
        }else{
            System.out.println("Ничья");
        }


    }
}
