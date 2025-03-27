package exam.xiecheng;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class question2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        Stack<Integer> stack = new Stack<>();
        int errorIndex = -1;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num > 0) {
                stack.push(num);
            } else {
                if (stack.isEmpty() || stack.peek() != -num) {
                    //第一个出错的位置
                    errorIndex = i + 1;
                    break;
                }
                stack.pop();
            }
        }
        //处理没有出错的情况
        if (errorIndex == -1 ) {
            System.out.println("1 1");
        } else {
            System.out.println(errorIndex + " " + (errorIndex + 1));
        }

    }

    private static boolean isValid(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int num : nums) {
            if (num <= 0) {
                if (!stack.isEmpty() && stack.peek() + num != 0) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(num);
            }
        }
        //如果能执行完上面的流程，最后栈里应该没有元素
        return stack.isEmpty();
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


/*
Stack<Integer> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        //每获取到一个整数就入栈，遇到一个负数就出栈，如果出栈的数与当前负数不是相反数，说明这个负数位置错了
        //记录当前的正数，后面出现这个相反数的时候，将当前负数的位置与前面出现的负数位置交换
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            if (num < 0) {
                int k = stack.pop();
                //说明当前位置出错了
                if (k + num != 0) {
                    res.add(i + 1);
                }
            } else {
                stack.push(num);
            }
        }
        System.out.println(res.get(0) + " " + res.get(1));
 */
