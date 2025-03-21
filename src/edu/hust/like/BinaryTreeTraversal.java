package edu.hust.like;

import java.util.*;

/**
 * 二叉树的遍历的四种方式；前序 根 左 右；中序 左 根 右；后序 左 右 根；层序
 * 分别用递归和迭代实现
 */
public class BinaryTreeTraversal {
    public static void main(String[] args) {
        /**
         * 给定一个二叉树，返回它的 前序 遍历。
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        //迭代利用栈实现的核心就是把不先访问的节点提前入栈，直到后入栈的节点访问完之后再开始处理这边的节点
        System.out.println("前序遍历（递归）：" + preOrderTraversalRecursive(root));
        System.out.println("前序遍历（迭代）：" + preOrderTraversalIterative(root));
        System.out.println("中序遍历（递归）：" + inOrderTraversalRecursive(root));
        System.out.println("中序遍历（迭代）：" + inOrderTraversalIterative(root));
        System.out.println("后序遍历（递归）：" + postOrderTraversalRecursive(root));
        System.out.println("后序遍历（迭代）：" + postOrderTraversalIterative(root));
        System.out.println("层序遍历（递归）：" + levelOrderRecursive(root));
        System.out.println("层序遍历（迭代）：" + levelOrderIterative(root));
    }

    //利用双向队列，先进先出，不断访问节点并且把左右节点再入队
    private static List<Integer> levelOrderIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.poll();
            res.add(node.val);
            if (node.left != null) {
                deque.offer(node.left);
            }
            if (node.right != null) {
                deque.offer(node.right);
            }
        }
        return res;
    }

    private static List<Integer> levelOrderRecursive(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        //利用递归的时候记录层数来保证节点保存到对应层
        levelOrder(root, 0, res);
        List<Integer> result = new ArrayList<>();
        for (List<Integer> list : res) {
            result.addAll(list);
        }
        return result;
    }

    private static void levelOrder(TreeNode root, int i, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        //给当前层创建一个list
        if (res.size() == i) {
            res.add(new ArrayList<>());
        }
        //访问当前层节点值放到对应层
        res.get(i).add(root.val);
        //递归左右子树，层数加1
        levelOrder(root.left, i + 1, res);
        levelOrder(root.right, i + 1, res);
    }

    //用两个栈保存，一个栈模拟递归过程，第二栈存储访问节点的数字顺序
    private static List<Integer> postOrderTraversalIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        //这样就能保证先把每个节点的子节点先出栈访问后再访问父节点，因为先入后出
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            //这里不反过来是因为后面stack2会反过来
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            res.add(stack2.pop().val);
        }
        return res;
    }

    private static List<Integer> postOrderTraversalRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postOrder(res, root);
        return res;
    }

    private static void postOrder(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(res, root.left);
        postOrder(res, root.right);
        res.add(root.val);
    }

    //用一个栈保存不断从左边遍历的节点，直到遇到null，则弹出一个节点，访问这个节点，然后对这个节点的右节点继续重复操作
    private static List<Integer> inOrderTraversalIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        //这里不能直接push一个节点，不然第二个while循环那里会重复存放节点
        //在while那里统一放进去
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            //遇到一个null即上面循环结束，root为null，弹出一个节点，访问这个节点，然后对这个节点的右节点继续重复操作
            TreeNode node = stack.pop();
            res.add(node.val);
            //若右节点为空，即不会再进行第二个循环，而是直接再弹出当前节点的父节点
            root = node.right;
        }
        return res;
    }

    private static List<Integer> inOrderTraversalRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrder(res, root);
        return res;
    }

    private static void inOrder(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(res, root.left);
        res.add(root.val);
        inOrder(res, root.right);
    }

    //创建一个栈，先放入头节点，再依次放入右节点，左节点，保证左节点先出来，然后不断出栈
    private static List<Integer> preOrderTraversalIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return  res;
        }

        //用一个栈即可
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            //先放入右节点，再放入左节点，注意要是为null就不放入，因为null在后面会被pop出来
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return res;
    }

    private static List<Integer> preOrderTraversalRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }

    private static void preOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

    static class TreeNode {
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
