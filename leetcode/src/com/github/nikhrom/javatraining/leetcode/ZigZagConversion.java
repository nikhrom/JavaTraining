package com.github.nikhrom.javatraining.leetcode;/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a
given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
 */


import java.util.Arrays;
import java.util.stream.Collectors;

public class ZigZagConversion {

    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;

        StringBuilder[] table = new StringBuilder[numRows];
        for(int i = 0; i < table.length; i++) table[i] = new StringBuilder();

        int line = 0;
        boolean toUp = false;
        for (int i = 0; i < s.length();) {
            if (line >= 0 && line < numRows) {
                table[line].append(s.charAt(i++));

                if(!toUp){
                    line++;
                }else{
                    line--;
                }
            } else {
                toUp = !toUp;
                if(toUp)
                    line = numRows - 2;
                else{
                    line = 1;
                }
            }
        }

        return Arrays.stream(table)
                .map(StringBuilder::toString)
                .collect(Collectors.joining());
    }

}
