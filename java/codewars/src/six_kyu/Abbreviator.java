//The word i18n is a common abbreviation of internationalization in the developer community, used
// instead of typing the whole word and trying to spell it correctly.
// Similarly, a11y is an abbreviation of accessibility.
//
// Write a function that takes a string and turns any and all
// "words" (see below) within that string of length 4 or greater into an
// abbreviation, following these rules:
//
//   1. A "word" is a sequence of alphabetical characters. By
// this definition, any other character like a space or hyphen
// (eg. "elephant-ride") will split up a series of letters into two
// words (eg. "elephant" and "ride").
//   2. The abbreviated version of the word should have the first
// letter, then the number of removed characters, then the last
// letter (eg. "elephant ride" => "e6t r2e").

package six_kyu;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Abbreviator{


    public String abbreviate(String string) {
        StringBuilder abbreviate = new StringBuilder();
        for(String word: parse(string))
            abbreviate.append(abbreviateWord(word));

        return abbreviate.toString();
    }

    private String abbreviateWord(String word){
        int lenghtWithoutExtreme = word.length() - 2;

        String abbreviate = "" + word.charAt(0) + lenghtWithoutExtreme
                + word.charAt(word.length() - 1);

        return (lenghtWithoutExtreme > 1) ? abbreviate: word;

    }

    private String[] parse(String text){
        return text.split("\\b");
    }


    public static void main(String[] args) {
        Abbreviator abrv = new Abbreviator();

        String string = abrv.abbreviate("on monolithic, monolithic5doggy; monolithic. doggy-double-barreled");

        System.out.println(string);
    }
}
