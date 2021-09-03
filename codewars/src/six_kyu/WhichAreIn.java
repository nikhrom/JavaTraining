/*Given two arrays of strings a1 and a2 return a sorted array r in lexicographical
order of the strings of a1 which are substrings of strings of a2.

        Example 1:
        a1 = ["arp", "live", "strong"]

        a2 = ["lively", "alive", "harp", "sharp", "armstrong"]

        returns ["arp", "live", "strong"]

        Example 2:
        a1 = ["tarp", "mice", "bull"]

        a2 = ["lively", "alive", "harp", "sharp", "armstrong"]

        returns []

        Notes:
        - Arrays are written in "general" notation. See "Your Test Cases" for examples in your language.
        - In Shell bash a1 and a2 are strings. The return is a string where words are separated by commas.
        - Beware: r must be without duplicates.*/


package six_kyu;

import java.util.Arrays;
import java.util.stream.Stream;

public class WhichAreIn {


    public static void main(String[] args) {
        String[] a1 = {"arp", "live", "strong"};
        String[] a2 = {"lively", "alive", "harp", "sharp", "armstrong"};
        System.out.println(Arrays.toString(inArray(a1, a2)));
    }


    public static String[] inArray(String[] array1, String[] array2) {

        return Stream.of(array1).filter((substring) -> {
            for (String string : array2)
                if (string.contains(substring))
                    return true;

            return false;

        })
                .sorted(String::compareTo)
                .toArray(String[]::new);
    }

}
