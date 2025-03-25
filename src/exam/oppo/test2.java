package exam.oppo;

import java.util.Scanner;

public class test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //获取第一个整数
        int n = in.nextInt();
        int[] arr = new int[n];
        //获取第二行的输入
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        //先求出当前数组中没有出现的最小非负整数多少
        //之后再遍历当前数组，如果遍历的到的值比当前最小非负整数小并且只有一个，则移除当前位置的数后数组中最小非负整数为当前数
        //创建一个相同长度的数组用于存放当前数组元素出现的次数，因为最小非负整数最大也就是当前数组的长度
        int[] temp = new int[n];
        //默认最小非负整数是0
        int mex = n;
        for (int i = 0; i < temp.length; i++) {
            if (arr[i] < n) {
                temp[arr[i]]++;
//                while (temp[mex] != 0) {
//                    mex++;
//                }
            }
        }
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0) {
                mex = i;
                break;
            }
        }
        //遍历原数组每个元素
        for (int i = 0; i < n; i++) {
            if (i < mex && temp[arr[i]] == 1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(mex);
            }
            if (i < n - 1) {
                System.out.print(" ");
            }
        }
    }
}
