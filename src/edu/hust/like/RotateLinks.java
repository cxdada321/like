package edu.hust.like;

/**
 * 翻转链表
 */
public class RotateLinks {
    public static void main(String[] args) {
        /**
         * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表
         * 链表可以选用迭代或递归方式完成反转
         */
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        //ListNode result = reverseIteration(head);
        ListNode result = reverseRecursion(head);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    private static ListNode reverseRecursion(ListNode head) {
        //如果没有之后的节点说明已经到了最后的节点开始返回
        //这里为什么需要判断head == null 是因为可能上层传来的节点是null，如果这个时候才用head.next获取下层节点就会报错
        if (head == null || head.next == null) {
            return head;
        }
        //保存最后层节点作为新的头节点，下层递归的节点
        ListNode newHead = reverseRecursion(head.next);
        //当前层的下层节点指回当前层
        head.next.next = head;
        //移除当前层的指向
        head.next = null;
        //返回当前层节点
        return newHead;
    }

    private static ListNode reverseIteration(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public static class ListNode {
        int val;
        ListNode next;
         ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
