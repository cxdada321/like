package edu.hust.like;

/**
 * 环形链表II
 */
public class CircleLinksII {
    public static void main(String[] args) {
        /**
         * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
         * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
         * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
         * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
         * 不允许修改 链表。
         * 是否可以使用 O(1) 空间解决此题
         */
        ListNode head = new CircleLinksII().new ListNode(3);
        ListNode node1 = new CircleLinksII().new ListNode(2);
        ListNode node2 = new CircleLinksII().new ListNode(0);
        ListNode node3 = new CircleLinksII().new ListNode(-4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;

    }

    //利用快慢指针，任意时刻，fast 指针走过的距离都为 slow 指针的 2 倍。
    //设链表中环外部分的长度为 a。slow 指针进入环后，又走了 b 的距离与 fast 相遇。
    //此时，fast 指针已经走完了环的 n 圈，因此它走过的总距离为 a+n(b+c)+b=a+(n+1)b+nc
    //因此，有a+(n+1)b+nc=2(a+b)⟹a=c+(n−1)(b+c)
    //有了 a=c+(n−1)(b+c) 的等量关系，会发现：从相遇点到入环点的距离加上 n−1 圈的环长，恰好等于从链表头部到入环点的距离。
    //因此两个节点相遇后，另外一个指针再开始从头部开始走，两个指针相遇的地方即为入环点
    private static ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        //如果没有相遇则继续循环
        while (fast != null) {
            slow = slow.next;
            //如果到了末尾为空了，说明没有环
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (slow == fast) {
                //相遇后另外个指针从开头走
                ListNode meet = head;
                while (slow != meet) {
                    slow = slow.next;
                    meet = meet.next;
                }
                return meet;
            }
        }
        return null;
    }


    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
