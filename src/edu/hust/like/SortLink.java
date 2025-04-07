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
    //为了在nlogn的时间复杂度下完成，需要利用归并排序实现，归并排序如果用递归会有一定的空间占用，如果需要常数空间下，需要采用迭代的方式
    //递归出口应该是当前节点不存在或者只有一个节点的时候返回，先是单个节点归并，然后把归并好的序列再拿去和上一层的节点归并，直到所有节点都排序
    public static ListNode sortList(ListNode head) {
        //开始归并是从头到尾，这样逐级返回最后才是需要的排列顺序
        return sortList(head, null);
    }

    private static ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return null;
        }
        //如果只有两个节点或者是一个节点，其尾部是null，则返回当前两个节点前面一个，并且移除节点的邻接关系，这样可以保证所有节点都会单个返回
        //先是单个归并，再是组合归并
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        //利用快慢指针来找到中间节点
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            //如果快指针还没到尾部，则多移动一步
            if (fast != tail) {
                fast = fast.next;
            }
        }
        //中间位置
        ListNode mid = slow;
        //继续把左右部分递归，直到递归到最底层会返回节点，把返回的节点归并
        ListNode head1 = sortList(head, mid);
        ListNode head2 = sortList(mid, tail);
        //归并后返回的是排序好的头节点，这样才可以用于下次归并
        return merge(head1, head2);
    }
    //归并
    private static ListNode merge(ListNode head1, ListNode head2) {
        //创建一个虚拟节点用于保存头节点
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                temp.next = head1;
                head1 = head1.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
            }
            temp = temp.next;
        }
        //如果还存在长的没有排序好的队列则直接放到尾部就好
        if (head1 != null) {
            temp.next = head1;
        } else if (head2 != null){
            temp.next = head2;
        }
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
