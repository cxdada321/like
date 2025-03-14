package edu.hust.like;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 划分字母区间
 */
public class PartitionLabels {
    public static void main(String[] args) {
        /**
         * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
         * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
         * 返回一个表示每个字符串片段的长度的列表
         */
        String s = "ababcbacadefegdehijhklij";
//        List<Integer> res = partitionLabels(s);
        List<Integer> res = partitionLabels1(s);
        res.forEach(System.out::println);
    }


    //这个其实就是贪心的思想，寻找每个片段可能的最小结束下标，因此可以保证每个片段的长度一定是符合要求的最短长度，如果取更短的片段，则一定会出现同一个字母出现在多个片段中的情况
    //可以不用hashset，直接用一个指针记录右区间，右区间用当前出现字符应该出现的最远位置，当左右指针相等时，说明这个片段符合要求，接着左右区间都移动到下一个位置继续寻找
    private static List<Integer> partitionLabels1(String s) {
        int[] arr = new int[26];
        List<Integer> res = new ArrayList<>();
        //这里记录最后出现的坐标，而不是记录次数，因为这样可以省去后面hashset对比的操作，即右区间的位置为前面出现字符的最远位置
        //如果是记录次数，那就涉及相同字符多次出现避免重复扩充右区间问题，就要用hashset记录
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a'] = i;
        }
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            right = Math.max(right, arr[c - 'a']);
            if (i == right) {
                res.add(right - left + 1);
                //左区间移动到下一个位置
                left = i + 1;
            }
        }
        return res;
    }


    //遍历一遍字符串，记录所有字符出现的次数，用一个数组保存，再次遍历数组的时候用一个hashset保存新出现的字符，如果出现的子串的字符都在hashmap中，那么就可以划分
    public static List<Integer> partitionLabels(String s) {
        int[] arr = new int[26];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }
        //记录一段字符串中应该出现的字符总数
        int sum = 0;
        int count = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                count++;
            } else {
                //如果set中不存在这个字母，判断count是否为0，如果为0，说明这个字母是新的，需要重新计数
                //如果count不为0，说明前面还有字母后面没有出现完，因此这个字符也应该和前面字符所在字符串一起
                set.add(s.charAt(i));
                //记录应该出现的字符总数，由于新加入的字符也算一位，因此count要加1
                count++;
                sum += arr[s.charAt(i) - 'a'];
            }
            //最后验证这个字符串是否符合要求
            if (count == sum) {
                res.add(sum);
                //识别完一个字符串后，清零重新计数
                sum = 0;
                count= 0;
            }
        }
        return res;
    }
}
