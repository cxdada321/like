package hust.edu;

import java.util.ArrayList;
import java.util.Arrays;

public class test5 {
    public static void main(String[] args) {
        /*给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
          返回这三个数的和。
          假定每组输入只存在恰好一个解*/
        int nums[] = {-1,2,1,-4};
        int target = 1;
        System.out.println(solution(nums, target));
    }

    private static int solution(int[] nums, int target) {
        //排序，升序操作，方便左右两个指针移动
        Arrays.sort(nums);
        //如果第一个数就大于目标值，那么最接近的就是前面三个数的和
        int min = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2 ; i++) {
            //定义左右两个指针；
            int left = i + 1 , right = nums.length - 1;
            //第一个元素去重，不然可能会重复找出相同的三元组，减少一次判断的情况
            if (i == 0 || nums[i] != nums [i-1]) {
                while (left < right) {
                    //记录三个值的大小
                    int sum = nums[i] + nums[left] + nums[right];
                    //比较三个值的大小，找到最接近的
                    if (Math.abs(target - min) > Math.abs(target - sum)) {
                        min = sum;
                    }
                    //偏大，右指针左移
                    if (sum > target) {
                        right--;

                    } else {
                        //偏小，左指针右移
                        left++;
                    }
                }
            }
            // //加一个限制条件，如果前三个数的和就大于目标值，那么后面的数的和肯定都大于目标值，不可能后面存在三个数相加更接近目标值
            if (nums[i] + nums[i + 1] + nums[i + 2] > target) {
                if (min > nums[i] + nums[i + 1] + nums[i + 2]) {
                    min = nums[i] + nums[i + 1] + nums[i + 2];
                    break;
                }
            }
        }
        return min;
    }
}
