package exam.xiecheng;

import java.util.Scanner;

/**
 * 时间问题
 */
public class question1 {
    public static void main(String[] args) {
        //获取输入
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] s = input.split(":");
        int curHours = Integer.parseInt(s[0]);
        int curMinutes = Integer.parseInt(s[1]);
        int res = 0;

        //检查所有时间
        for (int i = 0; i < 1440; i++) {
            String time = String.format("%02d%02d", curHours, curMinutes);
            if (isPalindrome(time)) {
                System.out.println(res);
                return;
            }
            curMinutes++;
            if (curMinutes >= 60) {
                curMinutes = 0;
                curHours++;
                if (curHours >= 24) {
                    curHours = 0;
                }
            }
            res++;
        }
    }

    private static boolean isPalindrome(String time) {
        return time.equals(new StringBuilder(time).reverse().toString());
    }
}
