package edu.hust.like;

import java.util.*;

/**
 * 单词拆分
 */
public class WordBreak {
    public static void main(String[] args) {
        /**
         * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
         * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
         */
        List<String> wordDict = List.of(new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"});
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        boolean res = wordBreak(s, wordDict);
        System.out.println(res);
    }

    //这个动态规划的核心就是：字符串s的每一段，都会遍历一次dp
    //例如，现在遍历到了字符串最后一位，现在遍历dp，然后某一位dp[j]出现了true，表示前j位可以在字典中找到单词
    //这时候后面个条件也成立的话，就相当于把问题分为了前一段和后一段，只要保证每次前一段是正确的，那每次就只需要考虑后一段是否存在匹配字典单词的字符串
    //为了避免重复计算，可以使用 动态规划 来优化算法。通过 动态规划（DP），可以将中间结果保存在一个数组中，避免重复计算
    public static boolean wordBreak(String s, List<String> wordDict) {
        //利用记忆化递归实现
        int n = s.length();
        int[] memory = new int[n];
        //给记忆数组赋值，用于表示切入点状态，为什么不用boolean，是因为用数字可以区分未处理和处理过这两种状态，便于递归里去掉重复操作
        //0表示切入点处理了，但是从这个点切入不能匹配到末尾
        Arrays.fill(memory, -1);
        return hasWord(s, 0, wordDict, memory);


//        int n = s.length();
//        //利用hash表保存字典，递归遍历字符串查看字典中是否存在对应单词
//        Set<String> dict = new HashSet<>();
//        wordDict.forEach(word -> dict.add(word));
//
//        //创建一个动态记忆数组，dp[i]表示s的前i个字符是否能由字典中的单词组成
//        boolean[] dp = new boolean[n + 1];
//        //空字符串可以由字典中的单词组成
//        dp[0] = true;
//
//        //最外一层遍历字符串元素
//        for (int i = 1; i <= n; i++) {
//            //对每个位置i，尝试查看从0到i之间的子字符串是否能匹配字典中的单词
//            for (int j = 0; j < i; j++) {
//                //如果dp[j]为true，则表明字符串前j个字母可以在字典中匹配到单词，且s.substring(j, i)是字典中的单词，则dp[i]为true
//                if (dp[j] && dict.contains(s.substring(j, i))) {
//                    dp[i] = true;
//                    break;
//                }
//            }
//        }
//        //进入递归
//        //boolean result = hasWord(s,0, dict, false);
//        return dp[n];
    }

    //递归在重复的长字符串下效果不佳，由于递归没有记忆化（如动态规划或回溯法中的“记忆化递归”），它的时间复杂度很高
    //如不采用动态规划，对于递归算法要采用记忆化递归，优化dfs
    private static boolean hasWord(String s, int index, List<String> wordDict, int[] memory) {
        //遍历完了整个字符串，表明前面的切点可以匹配到字典末尾
        if (index == s.length()) {
            return true;
        }
       if (memory[index] != -1) {
           //表示已经处理过了，跳过
           return memory[index] == 1;
       }
        for (String word : wordDict) {
            //如果某个起点开始可以匹配到字典的某个单词，则从这个起点开始继续往后匹配，深度递归，再从后返回结果，如果返回的结果为true
            //表示此切点可以匹配字典单词，把该切点位置赋值1
            if (s.startsWith(word, index) && hasWord(s, index + word.length(), wordDict, memory)) {
                //处理且成功
                memory[index] = 1;
                return true;
            }
        }
        //处理且失败
        memory[index] = 0;
        return memory[0] == 1;
    }
}
