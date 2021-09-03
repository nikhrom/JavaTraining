package git.nikhrom.algorithms;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class InsertionSort {

    public static <T> void sort(List<T> list, Comparator<T> comparator){
        for(int i = 1; i < list.size(); i++){
            T current = list.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(current, list.get(j)) < 0){
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j+1, current);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Integer> integers = new ArrayList<>();
        for(int i = 0; i < 10000000; i++)
            integers.add(random.nextInt());

        sort(integers, Integer::compareTo);


    }

}
