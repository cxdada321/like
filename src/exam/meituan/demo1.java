package exam.meituan;

import java.util.Scanner;

/**
 * 解密字符串
 */
public class demo1 {
    /**
     * 一个字符串s由数字和字母组成，现在需要对这个字符串进行解密，解密规则如下：
     * 初始字符串t为空，记录位移整数p为0
     * 对s中的每个字符进行一下操作：
     * 如果s的第i个字符为数字x，则对p修改：若p=0，则p=x；否则p=10p + x
     * 如果s的第i个字符不为数字，则先将字符串t左移p位，左移出去的字符串拼接左移完后的字符串末尾，随后将p重新置为0
     * 再对t修改:若这个字符为R，则将t逆序；若不为R，则将这个字符拼接到t的末尾
     * 最后直接输出t
     *
     *
     * 输入第一行是一个整数，表示测试数据组数
     * 后面每一行都是一个由大小写字母混合数字组成的字符串
     * @param args
     */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        for (int i = 0; i < n; i++) {
            String s = in.nextLine();
            System.out.println(decrypt(s));
        }
    }

    private static String decrypt(String s) {
        String t = "";
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                if (p == 0) {
                    p = c - '0';
                } else {
                    p = p * 10 + c - '0';
                }
            } else {
                //字符不为数字，则先将字符串t左移p位，左移出去的字符串拼接左移完后的字符串末尾，随后将p重新置为0
                t = leftShift(t, p);
                p = 0;
                //再对t修改:若这个字符为R，则将t逆序；若不为R，则将这个字符拼接到t的末尾
                if (c == 'R') {
                    t = new StringBuilder(t).reverse().toString();
                } else {
                    t += c;
                }
            }
        }
        return t;
    }

    private static String leftShift(String t, int p) {
        return t.substring(p) + t.substring(0, p);
    }
}
