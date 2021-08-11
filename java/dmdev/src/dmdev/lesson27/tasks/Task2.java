/*Написать программу, выполняющую поиск в строке
* шестнадцатеричных чисел, записанных по правилам Java,
* с помощью регулярных выражений и выводящую их на страницу. */

package dmdev.lesson27.tasks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {

    public static void main(String[] args) {

        String regex = "0[xX][0-9a-fA-F]+";
        String input = "awdawd 0x9A ndjawndiand 0X9a   0 x 123  0x0 dawpojdoiawd 22133 dkwefkd";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

}
