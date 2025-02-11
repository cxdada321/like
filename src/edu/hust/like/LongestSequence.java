package edu.hust.like;

import java.util.*;

public class LongestSequence {
    public static void main(String[] args) {
        /**
         * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
         * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
         * O(n)的算法也可以是O(2n)、O(3n)等
         */
        int[] nums = {100, 4, 200, 1, 3, 2};
        int length1 = longestConsecutive(nums);
        //利用动态规划的思想
        int length = longestConsecutive2(nums);
        System.out.println(length);
    }
    private static int longestConsecutive2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        //对数组排序
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            //判断map中是否有当前元素的前一个元素，如果有的话就长度加1，没有就是1
            int get = map.getOrDefault(nums[i] - 1, 0) + 1;
            //map中添加当前元素以及以这个元素结尾序列的长度
            map.put(nums[i], get);
            if (res < get) {
                res = get;
            }

        }
        return res;
    }


    public static int longestConsecutive(int[] nums) {
        //利用hash表查找一个元素的前后端元素是否存在
        Map<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], true);
        }
        //用于记录当前连续序列的长度
        int maxLength = 0;
        //查询map中的数据
        for (Integer i : map.keySet()) {
            if (map.get(i)) {
                int currentLength = 1;
                int index = i;
                //如果存在前端或者后端元素，就继续查找
                //利用递归分别查找一个元素左右是否存在符合的元素，找到就移除，这样就能避免重复查找
                currentLength = findLeft(map, index, currentLength) + findRight(map, index, currentLength) - 1;
                maxLength = Math.max(maxLength, currentLength);
            }
        }
        return maxLength;
    }

    private static int findRight(Map<Integer, Boolean> map, Integer i, int currentLength) {
        //递归出口
        if(!map.containsKey(i + 1)) {
            return currentLength;
        }
        //移除已经查找过的元素
        map.put(i, false);
        currentLength++;
        i++;
        return findRight(map, i, currentLength);
    }

    private static int findLeft(Map<Integer, Boolean> map, Integer i, int currentLength) {
        //递归出口
        if(!map.containsKey(i - 1)) {
            return currentLength;
        }
        currentLength++;
        i--;
        return findLeft(map, i, currentLength);
    }
}
