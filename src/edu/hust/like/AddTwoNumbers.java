package edu.hust.like;

/**
 * 两数相加
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        /**
         * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
         * 请你将两个数相加，并以相同形式返回一个表示和的链表。
         * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头
         */
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;
        ListNode r1 = new ListNode(5);
        ListNode r2 = new ListNode(6);
        ListNode r3 = new ListNode(4);
        r1.next = r2;
        r2.next = r3;
//        ListNode res = addTwoNumbers(l1, r1);
        //精简下代码
        ListNode res = addTwoNumbers1(l1, r1);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    private static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode res = null, temp = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            //用来当链表已经遍历完了，但是为了和后面计算统一，赋一个0值
            int n1 = l1 == null ? 0 : l1.val;
            int n2 = l2 == null ? 0 : l2.val;
            if (temp == null) {
                res = temp = new ListNode((n1 + n2 + carry) % 10);
            } else {
                temp.next = new ListNode((n1 + n2 + carry) % 10);
                temp = temp.next;
            }
            carry = (n1 + n2 + carry) / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry != 0) {
            temp.next = new ListNode(carry);
        }
        return res;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //用来保存结果链表的头部
        ListNode res = new ListNode(0);
        ListNode temp = res;
        int carry = 0;
        //遍历最短的链表长度求和，剩余长度的链表根据前面的进位和当前节点的值求和
        while (l1 != null && l2 != null) {
            temp.val = (l1.val + l2.val + carry) % 10;
            //这两个节点值求和后的进位
            carry = (l1.val + l2.val + carry) / 10;
            l1 = l1.next;
            l2 = l2.next;
            //如果后面没有节点了就不再创建节点了
            if (l1 != null || l2 != null) {
                //创建一个新的节点
                temp.next = new ListNode(0);
                temp = temp.next;
            }
        }
        //如果l1和l2长度不一样，剩余没有加完的链表继续和前面的进位求和
        while (l1 != null) {
            temp.val = (l1.val + carry) % 10;
            //这两个节点值求和后的进位
            carry = (l1.val + carry) / 10;
            l1 = l1.next;
            //如果后面没有节点了就不再创建节点了
            if (l1 != null) {
                //创建一个新的节点
                temp.next = new ListNode(0);
                temp = temp.next;
            }
        }
        while (l2 != null) {
            temp.val = (l2.val + carry) % 10;
            //这两个节点值求和后的进位
            carry = (l2.val + carry) / 10;
            l2 = l2.next;
            //如果后面没有节点了就不再创建节点了
            if (l2 != null) {
                //创建一个新的节点
                temp.next = new ListNode(0);
                temp = temp.next;
            }
        }
        //如果最后一位有进位，再创建一个节点
        if (carry != 0) {
            temp.next = new ListNode(carry);
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
