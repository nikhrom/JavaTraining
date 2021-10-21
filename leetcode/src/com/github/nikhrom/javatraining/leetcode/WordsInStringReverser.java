/*
Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.
Note that s may contain leading or trailing spaces or multiple spaces between two words.
The returned string should only have a single space separating the words. Do not include any extra spaces.
*/


package com.github.nikhrom.javatraining.leetcode;

public class WordsInStringReverser {

    static public String reverseWords(String s) {
        var words = s.trim().split("\\W+");
        StringBuilder result = new StringBuilder();
        for(int i = words.length - 1; i > 0; i--)
            result.append(words[i]).append(' ');
        result.append(words[0]);
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("  Bob    Loves  Alice   "));
    }

}
