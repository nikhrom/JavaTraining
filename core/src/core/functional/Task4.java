/*
Дан список целых чисел. Вывести строку, представляющую
собой конкатенацию строковых представлений этих чисел.
Например: список {1, 2, 3, 4, 5}
Результат: "12345"
 */

package core.functional;

import java.util.List;
import java.util.stream.Collectors;

public class Task4 {

    public static void main(String[] args) {
        List<Integer> values = List.of(1, 2, 3, 4, 5);

//        Мой вариант
//        values.stream()
//                .map(value -> value.toString())
//                .reduce((v1, v2) -> v1 + v2)
//                .ifPresent(System.out::println);

        String result = values.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        System.out.println(result);

    }

}
