package hust.edu;

import java.awt.*;
import java.util.*;
import java.util.List;

public class test4 {
    public static void main(String[] args) {
        /*给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
        同时还满足 nums[i] + nums[j] + nums[k] == 0 。
        请你返回所有和为 0 且不重复的三元组。
        注意：答案中不可以包含重复的三元组。*/

        int[] nums = {0,0,0};
        System.out.println(get(nums));
    }

    private static List<List<Integer>> get(int[] nums) {
        //定一求二，利用双指针解决，尝试采用hash表，但是存在重复的问题，所以采用排序
        List<List<Integer>> list = new ArrayList<>();
        //排序，升序操作，方便左右两个指针移动
        Arrays.sort(nums);
        //优化，可以给遍历加一个限定值，因为先进行了排序，如果第一个数字都大于0，那么后面的数字肯定都大于0，不可能存在三个数相加等于0
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {

            //第一个元素去重，不然可能会重复找出相同的三元组，减少一次判断的情况
            if (i == 0 || nums[i] != nums[i - 1]) {
                //定义左右两个指针；
                int left = i + 1, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[left] + nums[right] + nums[i];
                    //找到了三元组
                    if (sum == 0) {
                        list.add(Arrays.asList(nums[left], nums[right], nums[i]));
                        //左右指针移动，去重，为什么去重，是防止下一个指针和前一个指针相同，会找出相同的三元组
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }

                        //移动指针
                        left++;

                        //说明左边的数太小，右移
                    } else if (sum < 0) {
                        left++;
                        //说明右边的数太大，左移
                    } else {
                        right--;
                    }
                }

            }

        }
        return list;
    }
}



