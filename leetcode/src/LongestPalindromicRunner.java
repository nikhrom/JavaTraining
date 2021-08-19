import addtwonumbers.ListNode;

import java.util.*;
import java.util.stream.Collectors;

public class LongestPalindromicRunner {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
    }

    static public boolean isPalindrome(String s, int start, int end){
        while(start < end){
            if(s.charAt(start) != s.charAt(end))
                return false;

            start++;
            end--;
        }
        return true;
    }

    static public String longestPalindrome(String s) {
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

// Правильное решение:
//    public String longestPalindrome(String s) {
//        if (s == null || s.length() < 1) return "";
//        int start = 0, end = 0;
//        for (int i = 0; i < s.length(); i++) {
//            int len1 = expandAroundCenter(s, i, i);
//            int len2 = expandAroundCenter(s, i, i + 1);
//            int len = Math.max(len1, len2);
//            if (len > end - start) {
//                start = i - (len - 1) / 2;
//                end = i + len / 2;
//            }
//        }
//        return s.substring(start, end + 1);
//    }
//
//    private int expandAroundCenter(String s, int left, int right) {
//        int L = left, R = right;
//        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
//            L--;
//            R++;
//        }
//        return R - L - 1;
//    }


}
