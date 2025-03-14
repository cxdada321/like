package edu.hust.like;

/**
 * 合并两个有序链表
 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        /**
         * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
         */
    }

    //循环遍历两个链表，比较两个链表的值，将小的值放到新链表中
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode temp = new ListNode();
        ListNode res = temp;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                temp.next = list1;
                temp = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                temp = list2;
                list2 = list2.next;
            }
        }
        return res;
    }

    public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
