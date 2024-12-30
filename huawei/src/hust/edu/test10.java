package hust.edu;

public class test10 {
    public static void main(String[] args) {
        /*给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
        你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
        利用位运算的知识
        1、交换律：a ^ b ^ c <=> a ^ c ^ b
        2、任何数于0异或为任何数 0 ^ n => n
        3、相同的数异或为0: n ^ n => 0*/
        int[] nums = {4,1,2,1,2};
        System.out.println(singleNumber(nums));
    }

    private static int singleNumber(int[] nums) {
        int num = 0;
        for (int i : nums) {
            num ^= i;
        }
        return num;
    }
}
