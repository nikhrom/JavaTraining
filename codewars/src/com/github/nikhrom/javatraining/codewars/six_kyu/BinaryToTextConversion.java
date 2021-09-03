package com.github.nikhrom.javatraining.codewars.six_kyu;

public class BinaryToTextConversion {

    public static String binaryToText(String binary) {
        return encodeFromBinaryString(split(binary));
    }

    public static String[] split(String text){
        String[] split = new String[text.length() / 8];
        for (int i = 0; i < split.length; i++)
            split[i] = text.substring(i * 8, i * 8 + 8);
        return split;
    }

    public static String encodeFromBinaryString(String[] splitBinaryString){
        StringBuilder builder = new StringBuilder();
        for (String binaryChar: splitBinaryString)
            builder.append(Character.valueOf((char)Integer.valueOf(binaryChar, 2).intValue()));

        return builder.toString();
    }


    public static void main(String[] args) {
        System.out.println(binaryToText(""));
    }

}
