package exam.wangyi.game;

import java.util.Arrays;
import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line1 = sc.nextLine().split(" ");
        int a = Integer.parseInt(line1[0]);
        int n = Integer.parseInt(line1[1]);
        int[] power = new int[n];
        String[] line2 = sc.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            power[i] = Integer.parseInt(line2[i]);
        }
        Arrays.sort(power);
        int curPower = a;
        int curCoins = 0;
        int max = 0;
        for (int i : power) {
            if (curPower >= i) {
                curCoins++;
            } else {
                int reduce = i - curPower;
                if (curCoins >= reduce) {
                    curCoins -= reduce;
                    curPower = i;
                    curCoins++;
                }
            }
            if (curCoins > max) {
                max = curCoins;
            }
        }
        System.out.println(max);
    }
}
