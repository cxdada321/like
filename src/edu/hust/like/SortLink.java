package edu.hust.like;

/**
 * 排序链表
 */
public class SortLink {
    public static void main(String[] args) {
        /**
         * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
         * 链表中节点的数目在范围 [0, 5 * 10^4] 内
         * -10^5 <= Node.val <= 10^5
         */
        ListNode head = new ListNode(4);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        ListNode result = sortList(head);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    //可以用优先队列保存，全部放进去之后再挨个取出来
    public static ListNode sortList(ListNode head) {

    }
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
