/*
Дан список целых чисел. Найти среднее всех нечётных чисел, делящихся на 5
 */

package com.github.nikhrom.javatraining.core.functional;

import java.util.List;

public class Task1 {

    public static void main(String[] args) {
        List<Integer> values = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                                    11, 12, 13, 14, 15);

        values.stream().mapToInt(value -> value)
                .filter(value -> (value % 5) == 0 && (value % 2) != 0)
                .average()
                .ifPresent(System.out::println);
    }
}
