package com.znothings.leetcode;

import org.w3c.dom.NodeList;

public class S2 {
}
//You are given two non-empty linked lists representing two non-negative integer
//s. The digits are stored in reverse order, and each of their nodes contains a si
//ngle digit. Add the two numbers and return the sum as a linked list.
//
// You may assume the two numbers do not contain any leading zero, except the nu
//mber 0 itself.
//
//
// Example 1:
//
//
//Input: l1 = [2,4,3], l2 = [5,6,4]
//Output: [7,0,8]
//Explanation: 342 + 465 = 807.
//
//
// Example 2:
//
//
//Input: l1 = [0], l2 = [0]
//Output: [0]
//
//
// Example 3:
//
//
//Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//Output: [8,9,9,9,0,0,0,1]
//
//
//
// Constraints:
//
//
// The number of nodes in each linked list is in the range [1, 100].
// 0 <= Node.val <= 9
// It is guaranteed that the list represents a number that does not have leading
// zeros.
//
// Related Topics 链表 数学
// 👍 5198 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    /**
     * 先算各位，再算十位、百位。。。
     * 从第一位开始算 ，然后算第二位，然后算第三位。。。
     * 递归算法：
     *     算第一位，然后算第二位...
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        //计算最小位
        ListNode sum ;
        int val = l1.val + l2.val;
        if (val >= 10) {
            ListNode next = new ListNode(1);
            sum = new ListNode(val-10,next);
        }else {
            sum = new ListNode(val);
        }

        if (l1.next !=null || l2.next !=null){
            ListNode sumTemp = addTwoNumbers(l1.next,l2.next);
            sum.next = addTwoNumbers(sum.next,sumTemp);
        }
        return sum;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {

    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
