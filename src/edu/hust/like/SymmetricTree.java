package edu.hust.like;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 对称二叉树
 */
public class SymmetricTree {
    public static void main(String[] args) {
        /**
         * 给你一个二叉树的根节点 root ， 检查它是否轴对称
         * 运用递归和迭代两种方法解决这个问题吗
         */
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(2);
        root.left = left;
        root.right = right;
        TreeNode left1 = new TreeNode(3);
        TreeNode right1 = new TreeNode(4);
        left.left = left1;
        left.right = right1;
        right1.left = new TreeNode(8);
        right1.right = new TreeNode(9);
        TreeNode left2 = new TreeNode(4);
        TreeNode right2 = new TreeNode(3);
        right.left = left2;
        right.right = right2;
        left2.left = new TreeNode(9);
        left2.right = new TreeNode(8);
        //递归
//        boolean res = isSymmetric(root);
        //递归简化，只用一个函数
        boolean res = isSymmetric2(root);
        //迭代
//        boolean res = isSymmetric1(root);
        System.out.println(res);
    }

    private static boolean isSymmetric2(TreeNode root) {
        return check(root.left, root.right);
    }

    private static boolean check(TreeNode l, TreeNode r) {
        if (l == null && r == null) {
            return true;
        }
        if (l == null || r == null || l.val != r.val) {
            return false;
        }
        return check(l.left, r.right) && check(l.right, r.left);
    }

    //把左右子树对称的节点取出来，然后比较两个节点的值是否相等，用两个队列存储节点
    private static boolean isSymmetric1(TreeNode root) {
        if (root.left == null && root.right == null) {
            return true;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        //创建两个队列2，分别存储左右子树的节点
        Queue<TreeNode> leftQueue = new LinkedList<>();
        Queue<TreeNode> rightQueue = new LinkedList<>();
        leftQueue.offer(left);
        rightQueue.offer(right);
        while (!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
            left = leftQueue.poll();
            right = rightQueue.poll();
            //如果左右节点都是null，那么继续循环
            if (left == null && right == null) {
                continue;
            }
            //比较是否相同
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            //将左右节点的左右节点按照对称的方式放入队列
            leftQueue.offer(left.left);
            leftQueue.offer(left.right);
            rightQueue.offer(right.right);
            rightQueue.offer(right.left);
        }
        //经过上面的循环，如果左右队列都为空，那么就是对称的
        return true;
    }

    //采用递归的方式，对左右节点分别递归进去，把数字拼接成字符串，然后比较两个字符串是否相等
    public static boolean isSymmetric(TreeNode root) {
        if (root.left == null && root.right == null) {
            return true;
        }
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        dfs_left(root.left, left);
        dfs_right(root.right, right);
        return left.toString().equals(right.toString());
    }

    //递归遍历右子树
    private static void dfs_right(TreeNode right, StringBuilder res) {
        if (right == null) {
            res.append("null");
            return;
        }
        //先将当前节点的值放入res中，然后再先继续递归当前节点的右节点和左节点，递归进去如果是null就不会继续递归
        res.append(right.val);
        dfs_right(right.right, res);
        dfs_right(right.left, res);
    }


    //递归遍历左子树
    private static void dfs_left(TreeNode left, StringBuilder res) {
        if (left == null) {
            res.append("null");
            return;
        }
        //先将当前节点的值放入res中，然后再先继续递归当前节点的左节点和右节点，递归进去如果是null就不会继续递归
        res.append(left.val);
        dfs_left(left.left, res);
        dfs_left(left.right, res);
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
