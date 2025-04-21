package edu.hust.like;

/**
 * 二叉树展开为链表
 * 原地算法（O(1) 额外空间）
 */
public class FlattenBinaryToLinkList {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
//        flatten(root);
        flatten1(root);
        while (root != null) {
            System.out.println(root.val);
            root = root.right;
        }
    }


    //原地空间，可以用找前驱节点的思想，root如果没有左节点则不用管右节点，如果存在左节点，则root的右节点前面必然是左子树最右边的节点pre
    //pre.right = root.right，root.right = pre,root.left = null，相当于把root右子树拼接到左子树最右边的节点，再把左子树变为右子树
    //然后root = root.right，这样挨个处理
    private static void flatten1(TreeNode root) {
            while (root != null) {
                if (root.left != null) {
                    TreeNode pre = root.left;
                    while (pre.right != null) {
                        pre = pre.right;
                    }
                    pre.right = root.right;
                    root.right = root.left;
                    root.left = null;
                }
                root = root.right;
            }
    }



    //利用分治的思想，先对左子树展开，展开后的结果尾部tail保留，然后对右子树展开，tail连接展开后的右子树即可
    public static void flatten(TreeNode root) {
        if (root == null || (root.right == null && root.left == null)) {
            return;
        }
        if (root.left == null) {
            flatten(root.right);
        } else {
            if (root.right != null) {
                flatten(root.left);
                TreeNode tail = root.left;
                while (tail.right != null) {
                    tail = tail.right;
                }
                flatten(root.right);
                TreeNode temp = root.right;
                root.right = root.left;
                root.left = null;
                tail.right = temp;
            } else {
                flatten(root.left);
                root.right = root.left;
                root.left = null;
            }
        }
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
        }
    }

}
