//Дан список строк. Найти количество уникальных строк
//длиной более 8 символов

package com.github.nikhrom.javatraining.core.functional;

import java.util.List;

public class Task2 {

    public static void main(String[] args) {
        List<String> strings = List.of("123456789", "123456789",
                "1234567890",
                "1234567",
                "1111111111",
                "1234567899", "1234567899");

//        мой вариант
//        int countUnique = strings.stream().filter(string -> string.length() > 8)
//                .collect(Collectors.toSet())
//                .size();

        long countUnique = strings.stream().filter(string -> string.length() > 8)
                .distinct()
                .count();


        System.out.println(countUnique);

    }
}
