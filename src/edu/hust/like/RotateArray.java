package edu.hust.like;

import java.util.Arrays;

public class RotateArray {
    public static void main(String[] args) {
        /**
         * 给定一个整数数组 nums，将数组中的元素向右轮转 k个位置，其中k非负数。
         */
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        //rotate(nums, k);
        rotate1(nums, k);
        Arrays.stream(nums).forEach(num -> System.out.println(num));
    }

    /**
     * 方法二：不创建新的数组，通过两次翻转即可，通过翻转数组的方式，整个翻转，再根据k值分成两个数组，对两个数组分别翻转即可；
     * 方法三：利用最大公约数来把数组分组处理，为什么用最大公约数
     * 因为采用的思想是，把数组分出多个循环段，多个循环段覆盖整个数组，每个循环段数字单个交换位置，最大公约数是数学推导过程得到的
     * 最大公约数的思想，gcd(x, y) = gcd(y, x % y) ：两个数x和y的最大公约数等于y和x % y的最大公约数，这样就能不断收敛到最大公约数
     * 原因：如果一个数能同时整除x和y，它也能整除x % y和y
     * @param nums
     * @param k
     */
    //在不创建新数组的情况下解决
    //gcd(n, k)的作用是确定有多少个独立的循环节，即每个循环节里的元素一直循环是会回到原来的位置的
    //循环节的数学推导：n×k = 最大公约数×最小公倍数
    //由于最终回到了起点，故该过程恰好走了整数数量的圈，不妨设为 a 圈；再设该过程总共遍历了 b 个元素。
    //因此，an是总移动的步长（跑的总米数），b是遍历的元素（多少步），每次移动的步数是k（每步长），总步长相同，因此有an = bk
    //即 an 一定为 n,k 的公倍数。又因为我们在第一次回到起点时就结束，因此 a 要尽可能小，故 an 就是 n,k 的最小公倍数 lcm(n,k)
    //因此 b就为 lcm(n,k)/k，这说明单次遍历会访问到 lcm(n,k)/k 个元素
    //为了访问所有元素应该遍历的次数， n/b = n,k/lcm(n,k) = gcd(n,k)
    private static void rotate1(int[] nums, int k) {
        //对于k大于数组长度的直接取余相当于已经转了一圈了
        int n = nums.length;
        k = k % n;
        //循环节数
        int count = gcd(n, k);
        for (int start = 0; start < count; start++) {
            //记录当前所在位置
            int curent = start + k;
            //记录下一次位置，这里整除是为了防止数组越界
            int next = (start + k) % n;
            //保存当前位置的值
            int pre = nums[start];
            while (curent != start) {
                //保存下一次的值
                int temp = pre;
                pre = nums[next];
                nums[next] = temp;
                curent = next;
                next = (curent + k) % n;
            }
        }
    }
    //最大公约数的思想
    // gcd(x, y) = gcd(y, x % y) ：
    //两个数x和y的最大公约数等于y和x % y的最大公约数
    //原因：如果一个数能同时整除x和y，它也能整除x % y
    //x % y就能把数越变越小，直到为0，输出就为最大公约数

    //可以用图形化的思想理解欧几里得算法，即不断用小的矩形把大的矩形分解，直到一个矩形被完全分解，则剩下的那个矩形即为最大公约数
    private static int gcd(int x, int y) {
        return y > 0 ? gcd (y, x % y) : x;
    }

    public static void rotate(int[] nums, int k) {
        //创建一个数组重新保存元素
        int[] res = new int[nums.length];
        //存放前面的元素的索引
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i + k < nums.length) {
                res[i + k] = nums[i];
            }else {
                res[index++] = nums[i];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = res[i];
        }
    }
}
