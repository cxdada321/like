package hust.edu;

import java.util.ArrayList;
import java.util.List;

public class test21 {
    public static void main(String[] args) {
        /**
         * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
         */
        int[] nums = {1, 2, 3};
        List<List<Integer>> res = permute(nums);


    }
    public static List<List<Integer>> permute(int[] nums) {
        //回溯的思想就是，第一个递归进去后，如果符合条件，就进入第二个递归，一直到最底层的递归函数
        //然后根据结束条件返回，然后进入上一层递归，退出上一层选择的值，继续选择，直到这一层都选完了，继续返回上一层递归
        //根据这样的思想就可以把所有的情况都遍历到
        //重点是从最后一层递归返回的时候开始想，不要从头开始想，这样会很乱

        //采用回溯算法，试探 + 回退”。它通过逐步构建解的过程，尝试所有可能的选择
        //当发现某一步的选择不满足条件时，就回退到上一步并尝试其他选择
        //可以通过减枝来减少不必要的计算

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(res, list, nums, 0);
        res.forEach(num -> System.out.println(num.toString()));
        return res;
    }

    private static void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums, int index) {
        //递归出口，找到一个解
        if (index == nums.length) {
            //这里需要重新new一个list，不然会导致list的值被修改，对应的res中的值也会变，因为list是引用类型，采用深拷贝
            res.add(new ArrayList<>(list));
            return;
        }

        for (int num : nums) {
            if (!list.contains(num)) {
                list.add(num);
                backtrack(res ,list, nums, index + 1);
                list.removeLast();
            }
        }
    }
}
