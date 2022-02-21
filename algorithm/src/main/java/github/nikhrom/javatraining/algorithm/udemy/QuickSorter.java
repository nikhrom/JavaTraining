package github.nikhrom.javatraining.algorithm.udemy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QuickSorter {

    public <T> void sort(List<T> list, Comparator<T> comparator) {
        if (list == null) return;

        sort(list, 0, list.size(), comparator);
    }

    public <T> void sort(List<T> list, int i, int j, Comparator<T> comparator) {
        if (list == null) return;
        if (i == j) return;

        int p = partition(list, i, j, comparator);

        sort(list, i, p, comparator);
        sort(list, p + 1, j, comparator);
    }

    private <T> int partition(List<T> list, int i, int j, Comparator<T> comparator) {
        int pivot = i;
        int s1Index = i;
        int s2Index = i;

        for (int k = i + 1; k < j; k++) {
            if (comparator.compare(list.get(k), list.get(pivot)) >= 0) {
                s2Index++;
            } else {
                s1Index++;
                Collections.swap(list, s1Index, k);
                s2Index++;
            }
        }

        Collections.swap(list, pivot, s1Index);
        return s1Index;
    }

}
