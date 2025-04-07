package edu.hust.like;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 每日温度
 */
public class DailyTempuratures {
    public static void main(String[] args) {
        /**
         * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
         * 其中 answer[i] 是指在第 i 天需要等几天才能出现更高的温度。
         * 如果之后都不会有更高的温度，请在该位置用 0 来代替
         */
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        //利用已知的结果跳跃遍历实现
//        int[] result = dailyTemperatures(temperatures);
        //这个题目可以用单调栈解决，因为每个元素入栈后，后面的元素要入栈，如果比栈顶元素大，说明找到了第一个比前面元素大的元素，即开始出栈
        //直到出到栈里的元素都比当前元素大了，栈里还剩的元素说明没找到更大的元素，就继续往后遍历，这样就是通过出栈的过程，减少了后面的元素需要与前面的元素比较的次数
        int[] result = dailyTemperatures1(temperatures);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    private static int[] dailyTemperatures1(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            //如果栈顶元素小于当前元素，即
            while (!deque.isEmpty() && temperatures[deque.peek()] < temperatures[i]) {
                int index = deque.poll();
                res[index] = i - index;
            }
            //如果小于当前元素则入栈
            deque.offerFirst(i);
        }
        return res;
    }

    //从右到左遍历，用右边已经记录的结果来判断下个数的右边是否有大于当前值的值
    //但是防止从当前数重复遍历当前数右部分的所有数，可以通过当前数右边的数即已经记录的结果中，减少这个遍历的过程
    //temp[i]即找当前数i右边的数i+1的temp[i + 1]记录的数，如果temp[i + 1] = 0，且i的数比i+1的数还大，说明右边不存在更大的数，temp[i] = 0
    //如果temp[i + 1] != 0，即跳转到i + 1 + temp[i + 1]这个数去判断是否比i的数大，这样通过跳转可以减少遍历次数
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        res[n - 1] = 0;
        //从右开始遍历每个数
        for (int i = n - 2; i >= 0; i--) {
            //先判断右边的数
            int index = i + 1;
            //一直循环
            while (true) {
                if (temperatures[i] < temperatures[index]) {
                    res[i] = index - i;
                    break;
                    //如果当前右边的数小，并且右边没有比当前右边的数更大的数了，即直接赋值为0
                } else if (res[index] == 0) {
                    res[i] = 0;
                    break;
                }
                //如果还存在比当前右边的数更大的数，把索引变到那个数的索引，继续比较
                index += res[index];
            }
        }
        return res;
    }
}
