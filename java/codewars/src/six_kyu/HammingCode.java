//Task 1: Encode function
//        Implement the encode function.

//        For example:
//        input: "hey"
//        --> 104, 101, 121                  // ASCII values
//        --> 01101000, 01100101, 01111001   // binary
//        --> 000111111000111000000000 000111111000000111000111 000111111111111000000111  // tripled
//        --> "000111111000111000000000000111111000000111000111000111111111111000000111"  // concatenated

//Task 2: Decode function:
//        Check if any errors happened and correct them. Errors will be only bit flips, and not a loss of bits:
//        111 --> 101 : this can and will happen
//        111 --> 11 : this cannot happen
//
//        Note: the length of the input string is also always divisible by 24 so that you can convert it to an
//        ASCII value.
//
//        For example:
//
//        input: "100111111000111001000010000111111000000111001111000111110110111000010111"
//        --> 100, 111, 111, 000, 111, 001, ...  // triples
//        -->  0,   1,   1,   0,   1,   0,  ...  // corrected bits
//        --> 01101000, 01100101, 01111001       // bytes
//        --> 104, 101, 121                      // ASCII values
//        --> "hey"


package six_kyu;

import java.util.*;
import java.util.Map.Entry;

public class HammingCode {
    public String encode(String text){
        StringBuilder builder = new StringBuilder();

        for (char ch: text.toCharArray()){
            String bits = Integer.toBinaryString((int)ch);
            for(int i = bits.length(); i < 8; i++)
                builder.append(tripleCharsInString("0"));
            builder.append(tripleCharsInString(bits));
        }

        return builder.toString();
    }

    public String decode(String bits) {
        StringBuilder builder = new StringBuilder(bits);

        int lengthBitsInEncodedText = builder.length();

        for (int i = 0; i < lengthBitsInEncodedText / 3; i++) {
            String tripleBit = builder.substring(i, i + 3);
            builder.replace(i, i + 3, "" + decodeFromRepeatedChar(tripleBit));
        }

        int lengthBitsInDecodedText = builder.length();
        for(int i = 0; i < lengthBitsInDecodedText / 8; i++){
            builder.replace(i, i + 8,  "" + (char)Integer.parseInt(builder.substring(i, i + 8), 2));
        }

        return builder.toString();
    }

    private static char decodeFromRepeatedChar(String tripleChar){
        Map<Character, Integer> map = new HashMap<>();
        for(char ch: tripleChar.toCharArray())
            map.put(ch, map.getOrDefault(ch, 0) + 1);

        Optional<Entry<Character, Integer>> maxEntry = map.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue));

        return maxEntry.get()
                .getKey();

    }

    private static String tripleCharsInString(String text){
        StringBuilder builder = new StringBuilder();
        for (char ch: text.toCharArray())
            builder.append("" + ch + ch + ch);
        return builder.toString();
    }

}
