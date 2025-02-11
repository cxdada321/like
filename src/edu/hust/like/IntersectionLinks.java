package edu.hust.like;

/**
 * 相交链表
 */
public class IntersectionLinks {
    public static void main(String[] args) {
        /**
         * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
         * 如果两个链表不存在相交节点，返回 null 。
         * 函数返回结果后，链表必须 保持其原始结构
         */
        ListNode headA = new ListNode(4);
        ListNode headB = new ListNode(5);
        ListNode node1 = new ListNode(8);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        headA.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        headB.next = node3;
        ListNode result = getIntersectionNode(headA, headB);
        System.out.println(result.val);
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA, pB = headB;
        //原理，先走完自己的链表，再走对方的链表，这样两个链表走的长度就是一样的了，如果存在相同的节点，就会相遇
        //如果不存在相同的节点，就会在null处相遇
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    static class ListNode {
        int val;
        //指向 下一个节点
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
