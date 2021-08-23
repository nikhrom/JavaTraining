package git.nikhrom.algorithms;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MergeSort {

    private static <T> List<T> merge(List<T> left, List<T> right, Comparator<T> comparator){
        List<T> merged = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while(leftIndex < left.size() && rightIndex < right.size()){
            T leftElement = left.get(leftIndex);
            T rightElement = right.get(rightIndex);
            if(comparator.compare(leftElement, rightElement) <= 0){
                merged.add(leftElement);
                leftIndex++;
            }else {
                merged.add(rightElement);
                rightIndex++;
            }
        }
        while (leftIndex < left.size())
            merged.add(left.get(leftIndex++));

        while (rightIndex < right.size())
            merged.add(right.get(rightIndex++));

        return merged;
    }

    public static <T> List<T> sort(List<T> list, Comparator<T> comparator) {
        if (list.size() <= 1) return list;

        int n = list.size() / 2;
        List<T> left = new ArrayList<>(list.subList(0, n));
        List<T> right = new ArrayList<>(list.subList(n, list.size()));

        left = sort(left, comparator);
        right = sort(right, comparator);

        return merge(left, right, comparator);
    }

    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10000000; i++)
            integers.add(random.nextInt(100));

        integers = sort(integers, Integer::compareTo);
        System.out.println(integers);
    }

}
