/*
Sieve of Eratothenes
 */


package com.github.nikhrom.javatraining.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sieve {

    public static List<Integer> sieve(int n){
        boolean[] marks = new boolean[n];
        Arrays.fill(marks, true);
        List<Integer> result = new ArrayList<>();

        result.add(2);
        for(int i = 3; i * i < n; i += 2){
            if(marks[i]){
                result.add(i);
                for(int j = i * i; j < n; j += i){
                    marks[j] = false;
                }
            }
        }

        for(int i = 3; i < n; i += 2){
            if (marks[i]){
                result.add(i);
            }
        }

        return result;
    }




    public static void main(String[] args) {

        long before = System.currentTimeMillis();
        List<Integer> sieve = sieve(10000);
        long after = System.currentTimeMillis();

        System.out.println(after - before);



        System.out.println(sieve);
    }

}
