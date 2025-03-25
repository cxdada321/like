package exam.oppo;

import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        //获取第一行输入的两个整数
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int t = in.nextInt();
        //将第二行的输入的每个数字放入数组
        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            sum += arr[i];
        }
        //保存结果
        int res = 0;
        //依次移除数组里的元素，判断最后和是否小于等于t
        for (int i : arr) {
            int temp = sum - i * 2;
            if (temp <= t && temp >= 0) {
                res++;
            }
        }
        System.out.println(res);
    }
}
