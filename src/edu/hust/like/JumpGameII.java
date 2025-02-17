package edu.hust.like;

/**
 * 跳跃游戏 II
 */
public class JumpGameII {
    public static void main(String[] args) {
        /**
         * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
         * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
         * 0 <= j <= nums[i]，i + j < n
         * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
         */
        int[] nums = {1,1,1, 1};
//        int res = jump(nums);
//        int res = jump2(nums);
        int res = jump3(nums);
        System.out.println(res);
    }

    private static int jump3(int[] nums) {
        int max = 0;
        int count = 0;
        int index = 0;
        while (index < nums.length - 1) {
            //后面还有数字需要跳
            count++;
            //如果当前位置可以直接跳到后面的位置，直接返回
            if (nums[index] + index >= nums.length - 1) {
                return count;
            }
            //不然说明需要下次跳跃，需要找到下次跳跃的最大范围
            //记录当前位置
            int temp = index;
            for (int i = temp + 1; i <= temp + nums[temp]; i++) {
                if (i > nums.length -1) {
                    break;
                }
                //查找后面的数字，找到最大的跳跃范围
                if (max < nums[i] + i) {
                    max = nums[i] + i;
                    index = i;
                }
            }

        }
        return count;
    }

    //采用贪心算法可以解决这个问题
    //即每次都取可走的最大步数，直到走到最后一个位置
    private static int jump2(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int count = 0;
        int max = 0;
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            //计算当前的最大覆盖范围
            max = Math.max(max, i + nums[i]);
            //如果遍历到了当前范围的末尾，则需要一次跳跃，并且把覆盖范围重新设置
            if (i == end) {
                count++;
                end = max;
                //为什么加入这个判断，因为如果范围已经覆盖了最后个位置，说明下次跳跃就已经能到最后了
                //不需要再继续寻找了
                //如果当前范围超出了最后一个位置，直接返回
                if (end >= nums.length - 1) {
                    return count;
                }
            }
        }
        return count;
    }

    //动态规划需要的时间比较久
    //记录每一个位置的最小跳跃次数，最后把最后个位置的次数返回即可
    //每个位置的跳跃次数是前面的最小跳跃次数加一，并且取前面各个位置的最小值即可
    public static int jump(int[] nums) {
        int[] count = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            //遍历到每个值，就对后面的值进行遍历，赋给后面的值最小跳跃次数
            for (int j = 1; j <= nums[i]; j++) {
                //防止越界
                if (i + j >= nums.length) {
                    break;
                }
                //当前位置要是次数为0，说明第一次到，即次数为前面位置的次数加一，如果当前位置有值了，就要判断当前位置的值和前面的值加一的最小值
                count[i + j] = count[i + j] == 0 ?  count[i] + 1 : Math.min(count[i + j], count[i] + 1);
            }
        }
        return count[nums.length - 1];
    }
}
