package hust.edu;

import java.util.HashSet;

import java.util.Set;

public class test3 {
    public static void main(String[] args) {
         /*给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

        你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。

        你可以按任意顺序返回答案。*/
        /*Set<Integer> set = new HashSet<>();
        int[] arr = {2, 1, 5, 5, 11, 3};
        //把数组存到hash表，再用数字从hash表第一个开始遍历，找到目标值减去当前值的差值，如果存在则找到
        //将找到的两个数字去数组中找是否存在，存在则找到
        for (int j : arr) {
            set.add(j);
        }
        int[] arr1 = new int[2];
        int target = 10;
        boolean flag = false;
        //找到目标值减去当前值的差值，如果存在则找到
        while (!flag) {
            for (int i : set) {
                if (set.contains(target - i)) {
                    flag = true;
                    arr1[0] = i;
                    arr1[1] = target - i;
                    break;
                }
            }
        }
        //用于存放找到的索引位置
        int[] arr2 = new int[2];
        //处理两个相同的数字
        if (arr1[0] == arr1[1]) {
            int index = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr1[0] == arr[i]) {
                    arr2[index] = i;
                    index++;
                }
            }
        }
        //将找到的两个数字去数组中找是否存在，存在则找到，并且返回索引
        for (int i = 0; i < arr.length; i++) {
            if (arr1[0] == arr[i]) {
                arr2[0] = i;
            } else if (arr1[1] == arr[i]) {
                arr2[1] = i;
            }
        }*/
        int[] nums = {5, 1, 2, 3, 11, 5};
        int target = 10;
        int arr2[] = new int[2];
        int count = 0;
        for(int i = 1; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                count++;
                if(nums[j - i] + nums[j] == target){
                    arr2 = new int[]{j - i, j};
                }
            }

        }
        System.out.println(count);
        for (int i : arr2) {
            System.out.println(i);
        }
    }
}
