package hust.edu;

import java.util.HashSet;
import java.util.Set;

public class test {
    public static void main(String[] args) {
        /*给定一个字符串 s ，请你找出其中不含有重复字符的 最长
          子串的长度。*/
        String s = "abcaacbbbadbcef";
        System.out.println(new test().lengthOfLongestSubstring(s));
    }
    public int lengthOfLongestSubstring(String s) {
//        char[] ss = s.toCharArray();
//        int max = 0;
//        //不断遍历字符串，左边先不动，滑动右边，出现重复，利用hash表移除重复项，即移除最左边元素
//        //不断移除，直到没有重复元素，或者用数组索引来做，计算最大长度，再继续移动左边，重复上述操作
//        Set<Character> set = new HashSet<>();
//        for (int start = 0, end = 0; end < ss.length; end++) {
//            while (set.contains(ss[end])) {
//                set.remove(ss[start++]);
//            }
//            set.add(ss[end]);
//            max = Math.max(max, end - start + 1);
//        }
//        set.forEach(cahr -> System.out.printf(cahr.toString()));
//        return max;

        //不用hash表，用数组索引
        char[] arr = s.toCharArray();
        //ASCII码表，128个字符，记录字符串里出现的字符的位置，如果重复就会有多个位置
        int[] idx = new int[128];
        for (int i = 0; i < 128; i++) {
            idx[i] = -1;
        }
        int max = 0;
        int left = 0;
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            //索引表上是否之前有字符出现过
            int index = idx[arr[i]];
            //位置不重复
            if (index == -1) {
                length += 1;
            } else {
                //位置重复，计算最大长度
                //且重复字符在起始长度的右侧，则把起始长度移动到重复字符的右侧
                if (left <= index) {
                    left = ++index;
                    length = i - index + 1;
                    //重复字符在起始长度的左侧，不用移动，长度延迟即可
                } else{
                    length++;
                }
            }
            max = Math.max(max, length);
            //记录字符位置
            idx[arr[i]] = i;
        }
        return max;
        }
    }
