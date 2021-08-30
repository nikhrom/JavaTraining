/*Given a string s, return the longest palindromic substring in s.*/

public class LongestPalindromicRunner {

    public static boolean isPalindrome(String s, int start, int end){
        while(start < end){
            if(s.charAt(start) != s.charAt(end))
                return false;

            start++;
            end--;
        }
        return true;
    }

    public static String longestPalindrome(String s) {
        int maxLeft = 0;
        int maxRight = 0;

        for(int i = 0; i < s.length(); i++){
            for(int j = i; j < s.length(); j++){
                char c = s.charAt(j);
                if(isPalindrome(s, i, j)){
                    if(j - i > maxRight - maxLeft){
                        maxLeft = i;
                        maxRight = j;
                    }
                }
            }
        }


        return s.isEmpty() ? "" : s.substring(maxLeft, maxRight + 1);

    }


}
