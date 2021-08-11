/*Написать программу, проверяющую, является ли введённая
строка адресом почтового ящика.
В названии почтового ящика разрешить использование только букв,
цифр и нижних подчёркиваний, при этом оно должно начинаться
с буквы.
Возможные домены верхнего уровня: .org .com
*/

package dmdev.lesson27.tasks;

import java.util.regex.Pattern;

public class Task1 {

    public static void main(String[] args) {
        String email = "nikhrom_2001@gmail.com";
        String regex = "[a-zA-Z]\\w*@[a-zA-Z]+\\.(org|com)";

        System.out.println(Pattern.matches(regex, email));

    }

}
