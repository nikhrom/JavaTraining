package git.nikhrom.algorithms;

import java.util.*;

public class QuickSort {

    public static <T> void sort(List<T> list, Comparator<T> comparator){
        sort(list, 0, list.size(), comparator);
    }

    public static <T> void sort(List<T> list, int i, int j, Comparator<T> comparator){
        if (i == j) return;

        int p = partition(list, i, j, comparator);

        sort(list, i, p, comparator);
        sort(list, p + 1, j, comparator);
    }

    private static <T> int partition(List<T> list, int i, int j, Comparator<T> comparator) {
        int pivot = i;
        int s1Index = i;
        int s2Index = i;

        for(int k = i + 1; k < j; k++){
            if (comparator.compare(list.get(k), list.get(pivot)) >= 0){
                s2Index++;
            }else{
                s1Index++;
                Collections.swap(list, s1Index, k);
                s2Index++;
            }
        }

        Collections.swap(list, pivot, s1Index);
        return s1Index;
    }

    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++)
            integers.add(random.nextInt(100));

        long timeMillis = System.currentTimeMillis();
        sort(integers, Integer::compareTo);
        System.out.println(System.currentTimeMillis() - timeMillis);
    }


}
