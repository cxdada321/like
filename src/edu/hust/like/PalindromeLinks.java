package edu.hust.like;

/**
 * 回文链表
 */
public class PalindromeLinks {
    public static void main(String[] args) {
        /**
         * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表
         * 如果是，返回 true ；否则，返回 false
         */
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        //boolean result = isPalindrome1(head);
        boolean result = isPalindrome2(head);
        System.out.println(result);

    }

    /**
     * 方法二：用快慢指针找到链表的中点，从中点开始将后半部分链表反转，然后重新从两个链表头开始移动对比是否相同
     * @param head
     * @return
     */
    private static boolean isPalindrome2(ListNode head) {
        //快慢指针找到终点
        ListNode fast = head, slow = head;
        while (fast.next != null && slow.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //翻转后半部分链表
        ListNode prev = slow, curr = slow.next;
        while (curr != null) {
            ListNode temp = curr.next;
            //指向前面的节点
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        //从两个链表头开始移动对比是否相同
        ListNode firstHalf = head, secondHalf = prev;
        //到终点结束
        while (secondHalf.next != firstHalf && secondHalf.next != firstHalf.next) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        //如果开始只有两个节点，是不会进入上面的循环的，需要额外判断
        return true & firstHalf.val == secondHalf.val;
    }

    /**
     * 方法一：将链表的值加入到字符串中，然后判断字符串是否为回文字符串
     * @param head
     * @return
     */
    public static boolean isPalindrome1(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            head = head.next;
        }
        String original = sb.toString();
        String reverseStr = sb.reverse().toString();
        if (original.equals(reverseStr)) {
            return true;
        }
        return false;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
