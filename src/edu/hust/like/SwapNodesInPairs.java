package edu.hust.like;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

/**
 * 两两交换链表中的节点
 */
public class SwapNodesInPairs {
    public static void main(String[] args) {
        /**
         * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
         */
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;
        ListNode res = swapPairs(l1);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //利用两个双向队列保存链表中的节点，再分别出栈形成链表
        Deque<ListNode> list1 = new LinkedList<>();
        Deque<ListNode> list2 = new LinkedList<>();
        ListNode temp = head;
        while (temp != null) {
            list2.addLast(temp);
            if (temp.next != null) {
                list1.addLast(temp.next);
                temp = temp.next.next;
            } else {
                temp = null;
            }
        }

        // 构建新链表
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!list1.isEmpty() || !list2.isEmpty()) {
            // 优先连接偶数位节点（原第二个节点）
            if (!list1.isEmpty()) {
                tail.next = list1.pollFirst();
                tail = tail.next;
            }
            // 连接奇数位节点（原第一个节点）
            if (!list2.isEmpty()) {
                tail.next = list2.pollFirst();
                tail = tail.next;
            }
        }
        tail.next = null; // 防止循环引用

        return dummy.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
