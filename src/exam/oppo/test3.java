package exam.oppo;

import java.util.*;

public class test3 {
    public static void main(String[] args) {
        /**
         * 有一个长度为n的数组a，里面的元素都是非负整数,对于a中的每个元素，删除该元素后数组中没有出现过的最小非负整数是多少
         * 输出每个数字对应的结果
         * 用时间复杂度较小的算法
         */
        Scanner in = new Scanner(System.in);
        //获取第一个整数
        int n = in.nextInt();
        int[] arr = new int[n];
        //获取第二行的输入
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        Map<Integer, Integer> freq = new HashMap<>();
        Set<Integer> elements = new HashSet<>();

        for (int i : arr) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
            elements.add(i);
        }

        int mex = 0;
        while (elements.contains(mex)) {
            mex++;
        }

        //保存好结果，一次性输出
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (freq.get(arr[i]) == 1 && arr[i] < mex) {
                sb.append(arr[i]);
            } else {
                sb.append(mex);
            }
            if (i < n - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}
