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


package addtwonumbers;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);

        ListNode result = addTwoNumbers(l1, l2);

        while (result != null){
            System.out.println(result.val);
            result = result.next;
        }

    }

    static public ListNode addTwoNumbers(ListNode l1, ListNode l2) {


        int sum = reversedListToInt(l1) + reversedListToInt(l2);

        List<Integer> sumList = new LinkedList<>();
        while (true){
            sumList.add(sum % 10);
            sum /= 10;

            if(sum == 0) break;
        }

        Collections.reverse(sumList);
        ListNode node = new ListNode(sumList.get(0));
        for(int i = 1; i < sumList.size(); i++)
            node = new ListNode(sumList.get(i), node);

        return node;
    }



    static int reversedListToInt(ListNode l){
        LinkedList<Integer> result = new LinkedList<>();
        while(l != null){
            result.add(l.val);
            l = l.next;
        }
        Collections.reverse(result);
        return result.stream()
                .mapToInt(value -> value)
                .reduce(0, (v1, v2) -> v1 * 10 + v2);
    };
}
