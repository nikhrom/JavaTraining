/*
https://leetcode.com/problems/remove-covered-intervals/

Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri),
remove all intervals that are covered by another interval in the list.
The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.
Return the number of remaining intervals.*/


package github.nikhrom.javatraining.algorithm.leecode.easy;


import java.util.Arrays;

public class CoveredIntervalsRemover {

    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, Arrays::compare);
        int count = 1;

        int left = intervals[0][0];
        int right = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (left < intervals[i][0] && right < intervals[i][1]) {
                count++;
            }

            if (right < intervals[i][1]) {
                left = intervals[i][0];
                right = intervals[i][1];
            }

        }

        return count;
    }

}
