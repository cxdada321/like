package edu.hust.like;

import java.util.List;

/**
 * K 个一组翻转链表
 */
public class ReverseNodesinkGroup {
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
        int k = 1;
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
        //递归出口，防止递归到了null之后还继续执行下面的内容
        if (head == null) {
            return null;
        }
        int count = 0;
        ListNode tail = head;
        while (++count < k && tail != null && tail.next != null) {
            tail = tail.next;
        }
        //如果节点数量等于一批次的结果，即交换
        if (count < k) {
            return head;
        }
        ListNode next = tail.next;
        //交换k个节点，原本的head节点是头部现在是尾部，原本的tail是尾部现在是头部节点
        reserve(head, k);
        //交换完之后原本的头节点现在是尾节点，指向下一段递归返回的头节点
        head.next = reverseKGroup(next, k);
        //原本尾部的节点变成了新的头部节点
        return tail;
    }

    private static void reserve(ListNode res, int k) {
        ListNode pre = null;
        ListNode cur = res;
        //k个节点
        for (int i = 0; i < k; i++) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
