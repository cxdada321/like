package edu.hust.like;

import java.util.List;

/**
 * K 个一组翻转链表
 */
public class ReverseNodesinkGroup {
    static ListNode next;
    static ListNode pre = new ListNode(0);
    static ListNode tail;
    public static void main(String[] args) {
        /**
         * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
         * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
         * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
         * 你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗
         */
        ListNode head = new ListNode(1);
        ListNode node = head;
        for (int i = 2; i <= 5; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        int k = 2;
        ListNode res = reverseKGroup(head, k);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    //可以用栈一批一批的解决，但是会占用额外的空间
    //采用递归解决，每次递归的长度是k，到达尾部之后就不再递归，返回当前节点，连接上层节点，再次返回，这样最上面一层的节点就是调转顺序后的尾部节点
    //注意节点连边的移除，不要形成环

    public static ListNode reverseKGroup(ListNode head, int k) {
        int count = 0;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
