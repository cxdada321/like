package edu.hust.like;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和
 */
public class CombinationSum {
    public static void main(String[] args) {
        /**
         * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。
         * 你可以按 任意顺序 返回这些组合。
         * candidates 中的 同一个数字可以无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
         * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
         */
        int[] candidates = {2};
        int target = 1;
        List<List<Integer>> result = combinationSum(candidates, target);
        result.forEach(System.out::println);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        //先给数组排列，方便后面回溯处理
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        backTrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private static void backTrack(int[] candidates, int target, int index, List<Integer> list, List<List<Integer>> res) {
        //添加进集合
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (target >= candidates[i]) {
                //当前数是可用的，添加进集合，进入下一层继续寻找
                list.add(candidates[i]);
                backTrack(candidates, target - candidates[i], i, list, res);
                //回溯
                list.remove(list.size() - 1);
            } else {
                return;
            }
        }
    }
}
