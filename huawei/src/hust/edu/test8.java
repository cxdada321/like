package hust.edu;

public class test8 {
    public static void main(String[] args) {
        /*给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
        请注意 ，必须在不复制数组的情况下原地对数组进行操作。*/
        int[] nums = {0, 1, 0, 3, 12};
        /*int count = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            //有0就跳过，记录0的个数
            if (nums[i] == 0) {
                count++;
                continue;
            }
            //非0的数字移到前面
            nums[index] = nums[i];
            index++;
        }
        //最后把0补全
        for (int i = 0; i < count; i++) {
            nums[nums.length-1-i] = 0;
        }
        for (int num : nums) {
            System.out.println(num);
        }*/

        //双指针的方法
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                //遇到非0则放到最左边的空位上
                int tmep = nums[i];
                nums[i] = nums[left];
                nums[left] = tmep;
                //左指针右移
                left++;
            }
        }
        for (int num : nums) {
            System.out.println(num);
        }

    }
}