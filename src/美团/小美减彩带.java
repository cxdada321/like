package 美团;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 小美减彩带
 */
public class 小美减彩带 {
    public static void main(String[] args) {
        /**
         * 小美的彩带是由一条长度为n 的彩带一直无限循环得到的，彩带的每一个位置都有一个颜色，用ai表示。因此当
         * i>n 时，ai = a(i-n)
         * 小美每次会从左往后或从右往左剪一段长度为x 的彩带，她想知道她每次剪下来的彩带有多少种颜色。
         */
        Scanner in = new Scanner(System.in);
        //获取输入
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        in.nextLine();//消耗换行符
        //保存不同颜色的set
        Set<Integer> set = new HashSet<>();

        //表示左右彩带开始减的坐标
        int left = 0, right = n - 1;
        //处理查询
        for (int i = 0; i < k; i++) {
            String[] s = in.nextLine().split(" ");
            int index;
            if (s[0].equals("L")) {
                //从左往右
                index = left + Integer.parseInt(s[1]);
                //下一次的左边界
                if (index > n) {
                    for (int j = left; j < n; j++) {
                        set.add(arr[j]);
                    }
                    left = (index) % n;
                    for (int j = 0; j < index % n; j++) {
                        set.add(arr[j]);
                    }
                } else {
                    for (int j = left; j < index; j++) {
                        set.add(arr[j]);
                    }
                    left = index;
                }
            } else {
                //从右往左
                index = right - Integer.parseInt(s[1]);
                //下一次的右边界
                if (index < 0) {
                    for (int j = right; j >= 0; j--) {
                        set.add(arr[j]);
                    }
                    right = n - (Math.abs(index) % n);
                    for (int j = n - 1; j > right; j--) {
                        set.add(arr[j]);
                    }
                } else {
                    for (int j = right; j > index; j--) {
                        set.add(arr[j]);
                    }
                    right = index;
                }
            }
            System.out.println(set.size());
            set.clear();
        }
    }
}
