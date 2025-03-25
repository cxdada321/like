package edu.hust.like;

/**
 * 删除链表的倒数第 N 个结点
 */
public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        //给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点
        //能使用一趟扫描实现吗
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode res = removeNthFromEnd(head, 5);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
    //使用快慢指针，等快指针先移动n个节点后，慢指针再移动，快指针停下来的时候慢指针所在位置就是要删除的节点
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0, head);
        ListNode fast = head, slow = pre;
        while(fast != null) {
            if (n <= 0) {
                slow = slow.next;
            }
            fast = fast.next;
            n--;
        }
        //保存慢节点的下个节点，用于删除
        slow.next = slow.next.next;
        return pre.next;
    }
    public static class ListNode {
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
}
