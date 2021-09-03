/*
Написать программу, бесконечно считывающую
пользовательские числа из консоли.
Эти числа представляют собой количество секунд.
При каждом вводе числа, должна создаваться новая задача,
которая засыпает на введённое число секунд и затем
выводит "Я спал N секунд".
Однако, нужно сделать так, чтобы все задачи выполнялись
в одном и том же потоке в порядке ввода.
Т.е. в программе есть 2 потока: главный и поток для
выполнения всех задач по очереди.
При вводе отрицательного числа программа должна
завершать свою работу.
 */

package core.lesson26.practice.task1;

import java.util.Scanner;
import java.util.concurrent.*;

public class EndlessReader {


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        BlockingDeque<Task> tasks = new LinkedBlockingDeque<>();
        TasksRunner runner = new TasksRunner(tasks);
        executor.submit(runner);

        Scanner reader = new Scanner(System.in);

        while (true) {
            int second = reader.nextInt();
            if (second < 0) {
                executor.shutdown();
                executor.awaitTermination(1L, TimeUnit.SECONDS);
                break;
            } else {
                tasks.add(new Task(second));
            }
        }
    }

}
