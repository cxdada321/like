package 美团;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 小美的数组删除 {
    /**
     * 小美有一个长度为n 的数组ai，他可以对数组进行如下操作：
     * ● 删除第一个元素，同时数组的长度减一，花费为x
     * ● 删除整个数组，花费为k×max（a），其中max（a）表示a 中未出现过的最小非负整数。例如，如果a=[0,1,2,4]，则max（a）=3
     * 小美想知道将a 数组全部清空的最小代价是多少，请你帮帮他吧
     */

    //还有种思路是从后往前遍历，因为最后元素都会被删除，删除最后个元素的时候max（a）必然是比当前小的数，除非当前数是0
    //因此用一个set从末尾开始保存当前数，max（a）从0开始，如果set中有max（a），则max（a）+1，直到找到一个不在set中的数
    //这样就可以从末尾开始遍历，减少存储
    //为什么从0开始，因为如果数组中没有0，那么最小未出现的数就是0
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());

        for (int group = 0; group < t; group++) {

            String[] s = sc.nextLine().split(" ");
            int n = Integer.parseInt(s[0]);
            long k = Integer.parseInt(s[1]);
            long x = Integer.parseInt(s[2]);
            int[] nums = new int[n];
            String[] s1 = sc.nextLine().split(" ");
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(s1[i]);
            }

            //把所有数放到一个map里，找出最小未出现的数字，之后删除的时候，如果删的数字比这个数字小即删除那个数字就是新的未出现的最小数字
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }


            long min = 0;
            for (int i = 0; i < n; i++) {
                if (!map.containsKey(i)) {
                    min = i;
                    break;
                }
                if (i == n - 1) {
                    min = n;
                }
            }
            long res =  k * min;
            for (int i = 0; i < n; i++) {
                map.computeIfPresent(nums[i], (key, value) -> value - 1);
                if (map.get(nums[i]) == 0) {
                    min = Math.min(min, nums[i]);
                }
                //删除第一个元素计算费用
                res = Math.min(res, ((i + 1) * x) + k * min);
            }
            System.out.println(res);
        }
    }
}
