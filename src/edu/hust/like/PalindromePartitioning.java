package edu.hust.like;

import java.util.*;

/**
 * 分割回文串
 */
public class PalindromePartitioning {
    static int[][] f;
    static int n;
    static List<List<String>> res = new ArrayList<List<String>>();
    static List<String> each = new ArrayList<String>();
    public static void main(String[] args) {
        /**
         * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案
         */
        String s = "aab";
        //回溯
//        List<List<String>> res = partition(s);
        //回溯+动态规划（处理回文数）
//        List<List<String>> res = partition1(s);
        //回溯+动态规划（处理回文数）+记忆化搜索
        List<List<String>> res = partition2(s);
        res.forEach(System.out::println);
    }

    //这里的处理就是递归调用了，就像是把一个大问题不断往里深入判断，并且内部判断的结果如果出错了，就整个都出问题，并且每次判断完后，要把当前的结果保存下来，然后继续往下判断
    //这里就不是完全用动态规划确定状态了，而是需要确定哪个状态再递归进去，相当于旧的值不是求出来了再求新的值，这样就不需要保证循环条件了
    private static List<List<String>> partition2(String s) {
        n = s.length();
        //f用来确定当前这段字符是否被判断过，0，表示没有处理，1表示是回文数，-1表示不是回文数
        f = new int[n][n];

        dfs2(s, 0);
        return res;
    }

    private static void dfs2(String s, int i) {
        if (i == n) {
            res.add(new ArrayList<>(each));
            return;
        }
        for (int j = i; j < n; j++) {
            if (isPalindrome(s, i, j) == 1) {
                each.add(s.substring(i, j + 1));
                dfs2(s,j + 1);
                each.removeLast();
            }
        }
    }

    private static int isPalindrome(String s, int i, int j) {
        if (f[i][j] != 0) {
            return f[i][j];
        }
        //处理一个数或者空的情况
        if (i >= j) {
            f[i][j] = 1;
            //两段相等则递归检查内部是否是回文数
        } else if (s.charAt(i) == s.charAt(j)) {
            f[i][j] = isPalindrome(s, i + 1, j - 1);
        } else {
            f[i][j] = -1;
        }
        return f[i][j];
    }


    //回溯结合动态规划来确定是否为回文数以提高运行速度，动态规划还能和记忆化搜索结合
    //将字符串 s 的每个子串 s[i..j] 是否为回文串预处理出来，使用动态规划即可。设 f(i,j) 表示 s[i..j] 是否为回文串
    //即这个字符串的前一个字符串s[i+1..j−1] 为回文串，并且这个字符首尾还相同，那肯定是回文串，i大于等于j的时候，也是回文串

    //没有结合记忆化搜索，先把所有符合条件的字符串找出来，再添加到集合
    //结合记忆化搜索，就在加入的过程中再找合适的字符串，并且记录下来，这样就不用重复找了

    //用动态规划查找回文数的循环条件核心是，计算新的值要用前面的值，因此前面的值应该要先算，循环条件根据前面的值的位置来确定
    //当作一个二维数组来看，就j>=i的是空或者一个字母，肯定是不用管的，因此矩阵其实只有上三角需要计算
    //如果i从最上面开始，新的值的老值在右下角还没计算，就出现的得不到新值的问题，所以要从下面开始
    //i从左边开始的话就会出现很多重复计算，因为下三角是不用管的，因此考虑i从最下面最右边开始


    private static List<List<String>> partition1(String s) {
        n = s.length();
        f = new int[n][n];
        //赋初值，处理i<=j的情况，即空字符串或者一个字符的情况
        for (int i = 0; i < f.length; i++) {
            Arrays.fill(f[i], 1);
        }
        for (int i = n; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                //判断是否是回文串
                f[i][j] = s.charAt(i) == s.charAt(j) && f[i + 1][j - 1] ==1 ? 1 : 0;
            }
        }
        dfs(s, 0);
        return res;
    }

    private static void dfs(String s, int i) {
        //当前字符指针要是已经到了字符串末尾，就可以把当前的符合条件的字符串集合加入结果
        if (i == n) {
            res.add(new ArrayList<>(each));
            return;
        }
        for (int j = i; j < n; j++) {
            if (f[i][j] == 1) {
                each.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                each.removeLast();
            }
        }
    }

    //利用回溯的思想，每次判断当前字符串能否是回文串，如果是就继续往后找，直到最后也是也是回文串，就加入结果集，如果不是就返回，并继续找下一个回文串
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> each = new ArrayList<>();
        trackBack(0,1, s, res, each);
        return res;
    }

    private static void trackBack(int start, int end, String s, List<List<String>> res, List<String> each) {
        //最后一条字符串是回文串也验证完毕后，start就会到字符串的末尾+1，这时候就可以把前面加入的字符串加入结果集
        if (start == s.length()) {
            //保存从当前字符后面合适的回文字符串用于简化操作

            res.add(new ArrayList<>(each));
            return;
        }
        for (int i = end; i <= s.length(); i++) {
            //如果当前字符段是回文数，则继续递归，如果不是则找下个字符段
            if (s.substring(start, i).equals(new StringBuilder(s.substring(start, i)).reverse().toString())) {
                each.add(s.substring(start, i));
                //找该符合条件字符串后面的字符串是否也合适
                trackBack(i, i + 1, s, res, each);
                //后面的字符串不符合要求，移除当前字符串，继续递归寻找下一个合适的字符串
                each.removeLast();
            }
        }
    }
}
