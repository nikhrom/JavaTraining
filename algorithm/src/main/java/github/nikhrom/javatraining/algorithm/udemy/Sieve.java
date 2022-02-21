/*
Sieve of Eratothene
 */


package github.nikhrom.javatraining.algorithm.udemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sieve {

    public List<Integer> sift(int n){
        if (n < 2) return new ArrayList<>();

        boolean[] marks = new boolean[n];
        Arrays.fill(marks, true);
        List<Integer> result = new ArrayList<>();

        result.add(2);
        for(int i = 3; i * i < n; i += 2){
            if(marks[i]){
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

}
