/*
Задан файл со стихотворением. Определить частоту
повторяемости всех букв в стихотворении, игнорируя регистр.
        Вывести результат в файл в виде:
        а - 15
        б - 7
        и т.д.
*/


package dmdev.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.cert.CollectionCertStoreParameters;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;

public class HomeworkTask1 {

    public static void main(String[] args) throws IOException {



        Path text = Path.of("resources", "text.txt");

        try(Stream<String> lines = Files.lines(text)){

            Map<Character, Long> collect = lines.map(String::chars)
                    .flatMapToInt(identity())
                    .mapToObj(value -> (char) value)
                    .map(Character::toLowerCase)
                    .filter(Character::isLetter)
                    .collect(Collectors.groupingBy(value -> value, Collectors.counting()));

            System.out.println(collect);

        }




    }

}
