package github.nikhrom.javatraining.algorithm.udemy;

import java.util.Comparator;
import java.util.List;

public class InsertionSorter {

    public <T> void sort(List<T> list, Comparator<T> comparator) {
        if (list == null) return;

        for (int i = 1; i < list.size(); i++) {
            T current = list.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(current, list.get(j)) < 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, current);
        }
    }

}
