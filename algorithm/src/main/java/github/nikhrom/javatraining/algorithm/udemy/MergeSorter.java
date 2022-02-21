package github.nikhrom.javatraining.algorithm.udemy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSorter {

    private <T> List<T> merge(List<T> left, List<T> right, Comparator<T> comparator) {
        List<T> merged = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            T leftElement = left.get(leftIndex);
            T rightElement = right.get(rightIndex);
            if (comparator.compare(leftElement, rightElement) <= 0) {
                merged.add(leftElement);
                leftIndex++;
            } else {
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

    public <T> List<T> sort(List<T> list, Comparator<T> comparator) {
        if (list == null) return null;
        if (list.size() <= 1) return list;

        int n = list.size() / 2;
        List<T> left = new ArrayList<>(list.subList(0, n));
        List<T> right = new ArrayList<>(list.subList(n, list.size()));

        left = sort(left, comparator);
        right = sort(right, comparator);

        return merge(left, right, comparator);
    }

}
