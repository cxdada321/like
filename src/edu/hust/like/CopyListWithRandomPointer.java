package edu.hust.like;

import java.util.HashMap;
import java.util.Map;

/**
 * 复制带随机指针的链表
 */
public class CopyListWithRandomPointer {
    public static void main(String[] args) {
        /**
         * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点
         * 构造这个链表的 深拷贝。��拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值
         * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态
         * 复制链表中的随机指针可以指向复制链表中的任何节点，也可以指向 null
         */
        Node head = new Node(7);
        Node node = head;
        node.next = new Node(13);
        node.random = null;
        node.next.random = head;
        node.next.next = new Node(11);
        node.next.next.random = node.next.next.next;
        node.next.next.next = new Node(10);
        node.next.next.next.random = node.next.next;
        node.next.next.next.next = new Node(1);
        node.next.next.next.next.random = head;
        Node res = copyRandomList(head);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    //用一个map保存原本链表每个节点的位置
    //用另一个map保存复制的链表每个位置的节点
    //然后再次遍历原本链表，根据原本链表的random指针，找到复制链表的random指针
    public static Node copyRandomList(Node head) {
        Node dummy = new Node(0);
        Node temp = dummy;
        Node originalHead = head;
        Map<Node, Integer> original = new HashMap<>();
        Map<Integer, Node> copy = new HashMap<>();
        int index = 0;
        while (head != null) {
            original.put(head, index);
            temp.next = new Node(head.val);
            copy.put(index, temp.next);
            head = head.next;
            temp = temp.next;
            index++;
        }

        //重新再次遍历复制链表，找到每个节点的random指针
        temp = dummy.next;
        //全部放入后，再重新遍历原本的链表，把复制链表里面节点之间的关系加上
        while (originalHead != null) {
            if (originalHead.random != null) {
                temp.random = copy.get(original.get(originalHead.random));
            }
            temp = temp.next;
            originalHead = originalHead.next;
        }
        return dummy.next;
    }


    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
