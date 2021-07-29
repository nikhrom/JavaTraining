//Дана Map<String, Integer>. Найти сумму всех значений,
//длина ключей которых меньше 7 символов

package dmdev.functional;

import java.util.Map;

public class Task3 {

    public static void main(String[] args) {
        Map<String, Integer> map = Map.of(
                "1234567", 2,
                "12345678", 3,
                "123456", 1,
                "12345", 2,
                "", 4
        );


        int sum = map.entrySet().stream()
                .filter(entry -> entry.getKey().length() < 7)
                .mapToInt(Map.Entry::getValue)
                .sum();

        System.out.println(sum);
    }

}
