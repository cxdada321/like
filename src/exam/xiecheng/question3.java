package exam.xiecheng;

import java.util.Scanner;

public class question3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            String s = sc.next();
            //定义一个数组 dp，其中 dp[i] 表示到达字符串 s 的前 i 个字符的合法演奏方式的数量
            //对于每个字符 s[i-1]，可以选择：
            //只按下当前的单个键（如果 s[i-1] 是 ‘1’ 到 ‘9’）。
            //按下前一个键和当前键组合（如果 s[i-2] 和 s[i-1] 组合成的数字在 10 到 26 之间）。
            long[] dp = new long[n + 1];
            //空字符串有一种演奏方式，什么都不按
            dp[0] = 1;
            for (int i = 1; i <= n; i++) {
                //考虑一个按键，如果 s[i-1] 是 ‘0’，则 dp[i] 不能通过单个键来增加，只能通过组合键来增加
                if (s.charAt(i - 1) != '0') {
                    dp[i] = (dp[i] + dp[i - 1]) & 1000000007;
                }
                //多个键的情况
                if (i > 1) {
                    int num = Integer.parseInt(s.substring(i - 2, i));
                    if (num >= 10 && num <= 26) {
                        dp[i] = (dp[i] + dp[i - 2]) % 1000000007;
                    }
                }
            }
            System.out.println(dp[n]);
        }
    }
}
