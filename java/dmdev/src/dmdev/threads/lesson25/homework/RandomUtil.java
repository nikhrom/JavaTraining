package dmdev.threads.lesson25.homework;

import java.util.Random;

public class RandomUtil {
    static final Random RANDOM_INTEGER = new Random();

    public RandomUtil(){};

    public static int getInt(int count){
        return RANDOM_INTEGER.nextInt(count) + 1;
    };

}
