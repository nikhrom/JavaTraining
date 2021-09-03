/*You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order, and each of their nodes contains a single digit.
Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.



Example 1:
        Input: l1 = [2,4,3], l2 = [5,6,4]
        Output: [7,0,8]
        Explanation: 342 + 465 = 807.

Example 2:
        Input: l1 = [0], l2 = [0]
        Output: [0]

Example 3:
        Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
        Output: [8,9,9,9,0,0,0,1]


Constraints:
        The number of nodes in each linked list is in the range [1, 100].
        0 <= Node.val <= 9
        It is guaranteed that the list represents a number that does not have leading zeros.*/


package com.github.nikhrom.javatraining.leetcode.addtwonumbers;


import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    static public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger sum = reversedListToInt(l1).add(reversedListToInt(l2));

        List<Byte> sumList = new LinkedList<>();
        while (true){
            sumList.add((byte) (sum.mod(BigInteger.TEN).intValue()));
            sum = sum.divide(BigInteger.TEN);

            if(sum.equals(BigInteger.ZERO)) break;
        }

        Collections.reverse(sumList);
        ListNode node = new ListNode(sumList.get(0));
        for(int i = 1; i < sumList.size(); i++)
            node = new ListNode(sumList.get(i), node);

        return node;
    }



    static BigInteger reversedListToInt(ListNode l){
        List<Byte> digits = new LinkedList<>();
        while(l != null){
            digits.add((byte)l.val);
            l = l.next;
        }
        Collections.reverse(digits);

        BigInteger bigint = BigInteger.ZERO;
        for (var value : digits) {
            bigint = bigint.multiply(BigInteger.TEN);
            bigint = bigint.add(BigInteger.valueOf(value));
        }

        return bigint;
    };
}
