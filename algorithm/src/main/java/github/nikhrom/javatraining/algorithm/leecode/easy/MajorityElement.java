/*
https://leetcode.com/problems/majority-element/

Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times.
You may assume that the majority element always exists in the array.
*/


package github.nikhrom.javatraining.algorithm.leecode.easy;

public class MajorityElement {

    public int getMajorityElement(int[] nums) {
        int result = nums[0];
        int appearances = 0;

        for (int i = 0; i < nums.length; i++) {
            if (result != nums[i]) {
                appearances--;
            } else {
                appearances++;
            }

            if (appearances < 0) {
                result = nums[i];
                appearances *= -1;
            }
        }

        return result;
    }

}
