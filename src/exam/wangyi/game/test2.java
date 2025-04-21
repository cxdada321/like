package exam.wangyi.game;

import java.util.Scanner;

public class test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long centerX = sc.nextLong();
        long centerY = sc.nextLong();
        long radius = sc.nextLong();

        int n = sc.nextInt();

        int num = 0;

        long square = radius * radius;

        for (int i = 0; i < n; i++) {
            long playerX = sc.nextLong();
            long playerY = sc.nextLong();

            long x = playerX - centerX;
            long y  = playerY - centerY;

            long distance = x * x + y * y;
            if (distance <= square) {
                num++;
            }
        }
        System.out.println(num);
    }
}
