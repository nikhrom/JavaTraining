package core.lesson27;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternReplaceExample {

    public static void main(String[] args) {
        String phoneNumber = "adawp +375(29)898-12-13 oawfkoifwaoif +375(44)818-12-44 awdawdawdaw +375 (29) 898-10-13";
        String regex = "(?:\\+375)? ?\\((?<code>\\d{2})\\) ?(\\d{3})-(\\d{2})-(\\d{2})";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()){
            matcher.appendReplacement(stringBuilder, "$2 - $3 - $4");

        }

        matcher.appendTail(stringBuilder);
        System.out.println(stringBuilder);
    }

}
