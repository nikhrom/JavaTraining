package com.github.nikhrom.javatraining.leetcode;

public class BinarySearch {

    static public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0;
        int right = nums.length - 1;
        int pos;


        while (left < right) {
            pos = (right - left) / 2 + left;

            if (nums[pos] < target) {
                left = pos;
            }else if (nums[pos] > target) {
                right = pos;
            }

            if(nums[pos] == target) return pos;

        }
        return -1;
    }

}
