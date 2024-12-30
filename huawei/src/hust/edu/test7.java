package hust.edu;

import java.util.*;

public class test7 {
    public static void main(String[] args) {
        /*给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
         子数组是数组中元素的连续非空序列。*/
        int[] nums = {1, -1 ,0};
        int k = 0;
        System.out.println(getNum(nums, k));
    }

    private static int getNum(int[] nums, int k) {
       /* 运行时间太长
       int count = 0;
        for (int i = 0; i < nums.length; i++) {
            //分组计算
            int end = i;
            for (int j = 0; j < nums.length - i; j++) {
                int start = j;
                int sum = 0;
                for (; start <= end + j; start++) {
                    sum += nums[start];
                }
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;*/

        //使用前缀和求出这个数组的每个序列和，这样就不用单独计算每个序列和了，一个序列[i,j]的和就是prefixSum[j+1]-prefixsum[i]
        //前缀和数组第一项为0，这样就不用单独计算第一个序列和了
        int count = 0;
        int[] arr1 = new int[nums.length + 1];
        arr1[0] = 0;
        //hashmap存放前缀和，如果存在[i,j]和为k，那必然是prefixSum[j+1]-prefixsum[i]=k，即prefixsum[i]=prefixSum[j+1]-k
        //所以每次加入一个值，就判断是否存在prefixSum[j+1]-k的值，如果存在，那么就找到了一个和为k的序列
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            arr1[i + 1] += arr1[i] + nums[i];
            //先判断，防止对自己进行判断
            if (map.getOrDefault(arr1[i + 1] - k, 0) != 0) {
                count += map.get(arr1[i + 1] - k);
            }
            //如果前缀不存在，则添加一个前缀和，如果存在则加一
            if (map.computeIfPresent(arr1[i + 1], (key, v) -> ++v) == null) {
                map.put(arr1[i + 1], 1);
            }
        }
        return count;
    }
}
