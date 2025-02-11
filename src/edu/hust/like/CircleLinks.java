package edu.hust.like;

/**
 * 环形链表
 */
public class CircleLinks {
    public static void main(String[] args) {
        /**
         * 给你一个链表的头节点 head ，判断链表中是否有环。
         * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
         * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
         * 进阶，O(1)（即，常量）内存解决此问题，利用快慢指针，快指针每次走两步，慢指针每次走一步，如果有环，快指针一定会追上慢指针
         * 如果不考虑内存，就用hash表存储每个节点，如果有重复的节点，就说明有环
         */
        ListNode head = new CircleLinks().new ListNode(3);
        ListNode node1 = new CircleLinks().new ListNode(2);
        ListNode node2 = new CircleLinks().new ListNode(0);
        ListNode node3 = new CircleLinks().new ListNode(-4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;
        boolean res = hasCycle(head);
        System.out.println(res);
    }

    //利用快慢指针
    private static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head, fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return  true;
    }


    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
