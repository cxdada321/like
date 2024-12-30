package hust.edu;

import java.util.ArrayList;
import java.util.List;

public class test23 {
    public static void main(String[] args) {
        /**
         * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的集（幂集）。
         * 解集不能包含重复的子集。你可以按 任意顺序 返回解集。
         */
        int[] nums = {1, 2, 3};
        List<List<Integer>> res = subsets(nums);
        res.forEach(num -> System.out.println(num.toString()));

    }
    public static List<List<Integer>> subsets(int[] nums) {
        //采用回溯的思想，每次都有两种选择，选或者不选
        List<List<Integer>> res = new ArrayList<>(List.of(new ArrayList<>()));
//        List<Integer> list = new ArrayList<>();
//        trackBack(res, list, nums, 0);

        //或者采用动态规划的思想，每次都是上一次的结果加上新的元素
        for (int i = 0; i < nums.length; i++) {
            int size = res.size();
            for (int j = 0; j < size; j++) {
                List<Integer> temp = new ArrayList<>(res.get(j));
                temp.add(nums[i]);
                res.add(temp);
            }
        }
        return res;
    }

    private static void trackBack(List<List<Integer>> res, List<Integer> list, int[] nums, int i) {
        //按着回溯算法一般格式
        /*//递归出口
        if (i == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        // 选择当前元素：将 nums[i] 加入子集
        list.add(nums[i]);
        // 递归到下一层
        trackBack(res, list, nums, i + 1);
        // 回溯，撤销选择
        list.remove(list.size() - 1);

        // 不选择当前元素：跳过当前元素，递归到下一层
        trackBack(res, list, nums, i + 1);*/


        //换一种格式，不用递归出口，靠for循环来控制
        //每次开始先加入上层的结果
        res.add(new ArrayList<>(list));
        
        //遍历数组，数组遍历完就会自动结束这一层的递归，就不用设置递归出口
        for (int j = i; j < nums.length; j++) {
            list.add(nums[j]);
            //这里新的回溯的时候起点应该是新的一层作为起点
            trackBack(res, list, nums, j + 1);

            //撤销选择
            list.removeLast();
        }
    }

}
