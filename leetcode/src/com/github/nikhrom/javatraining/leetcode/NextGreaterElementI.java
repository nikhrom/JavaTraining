/*The next greater element of some element x in an array is the first greater element that is to
the right of x in the same array.
You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine
the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.*/

package com.github.nikhrom.javatraining.leetcode;

import java.util.*;

public class NextGreaterElementI {

    static public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < nums2.length; i++){
            var current = nums2[i];
            while(!stack.isEmpty() && stack.peek() < current){
                map.put(stack.pop(), current);
            }
            stack.push(current);
            map.put(current, -1);
        }

        int[] ans = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++){
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }

}
