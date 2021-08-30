/*
Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s
atoi function).

The algorithm for myAtoi(string s) is as follows:

Read in and ignore any leading whitespace.
Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it
is either. This determines if the final result is negative or positive respectively. Assume the result is positive if
neither is present.
Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the
string is ignored.
Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0.
Change the sign as necessary (from step 2).
If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains
in the range. Specifically, integers less than -2^31 should be clamped to -2^31, and integers greater than 2^31 - 1
should be clamped to 2^31 - 1.
Return the integer as the final result.
Note:

Only the space character ' ' is considered a whitespace character.
Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
 */


public class StringToInteger {

    public static int myAtoi(String s) {
        long result = 0;
        char sign = '\0';
        boolean numberFound = false;

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            if (Character.isDigit(currentChar)) {
                result *= 10;
                result += (int) currentChar - 48;
                numberFound = true;

                long temp = (sign == '-') ? -1 * result : result;
                if (temp >= Integer.MAX_VALUE)
                    return Integer.MAX_VALUE;
                else if (temp <= Integer.MIN_VALUE)
                    return Integer.MIN_VALUE;

            } else if (sign == '\0' && !numberFound && (currentChar == '-' || currentChar == '+')) {
                sign = currentChar;
            } else if (!Character.isDigit(currentChar) &&
                    sign != '\0' && numberFound && currentChar != ' ') {

                break;
            }


        }

        return (sign == '-') ? -1 * (int) result : (int) result;
    }
}
