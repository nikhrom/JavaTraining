// Suppose we know the process by which a string s was encoded
// to a string r (see explanation below). The aim of the kata is to decode
// this string r to get back the original string s.
//
// Explanation of the encoding process:
// 1. input: a string s composed of lowercase letters from "a" to "z", and a positive
// integer num
// 2. we know there is a correspondence between
// abcde...uvwxyz and 0, 1, 2 ..., 23, 24, 25 : 0 <-> a, 1 <-> b ...
// 3. if c is a character of s whose corresponding number is x,
// apply to x the function f: x-> f(x) = num * x % 26, then find ch the
// corresponding character of f(x)
// 4. Accumulate all these ch in a string r
// 5. concatenate num and r and return the result
//
// For example:
// encode("mer", 6015)  -->  "6015ekx"
//
//  m --> 12,   12 * 6015 % 26 = 4,    4  --> e
//  e --> 4,     4 * 6015 % 26 = 10,   10 --> k
//  r --> 17,   17 * 6015 % 26 = 23,   23 --> x
// So we get "ekx", hence the output is "6015ekx"
//
// Task
// A string s was encoded to string r by the above process. complete the function to get back s whenever it is possible.
// Indeed it can happen that the decoding is impossible for strings composed of whatever
// letters from "a" to "z" when positive integer num has not been correctly chosen. In that case
// return "Impossible to decode".
//
// Examples
// decode "6015ekx" -> "mer"
// decode "5057aan" -> "Impossible to decode"

package six_kyu;

public class ReversingProcess {

    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public static String decode(String r) {
        String[] split = split(r);
        StringBuilder message = new StringBuilder();



        try{
            long key = Long.parseLong(split[0]);
            if(key % 2 == 0 || key % 13 == 0)
                throw new Exception("Impossible to decode");

            String encodedMessage = split[1];

            for (char chr : encodedMessage.toCharArray())
                message.append(decodeLetter(chr, key));
        }catch (Exception e){
            return e.getMessage();
        }

        return message.toString();
    }

    public static char decodeLetter(char letter, long key) throws Exception{
        for(long i = 0; i < alphabet.length(); i++){
            int res = (int)(i * key % alphabet.length());

            if(alphabet.charAt(res) == letter)
                return alphabet.charAt((int)i);
        }
        throw new Exception("Impossible to decode");
    }

    public static String[] split(String text) {
        return text.split("(?=[a-z])", 2);
    }

    public static void main(String[] args) {

        System.out.println(decode("1877138eieaqgumi"));
    }

}
