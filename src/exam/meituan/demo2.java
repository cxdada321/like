package exam.meituan;

import java.util.Scanner;

/**
 * 小美架炮
 */
public class demo2 {
    /**
     * 在一个无限大的棋盘有n个炮，第i个炮的位置为(x[i],y[i])
     * 每个炮的攻击方式是，先选一个方向，该方向上的第一个棋子是炮架，通过炮架可以攻击到炮架后面的棋子（只能攻击到炮架后面的第一个）
     * 求出每个炮第一次攻击能够攻击到多少炮
     *
     * 输入第一行是炮的数量
     * 接下来n行，每行输入两个整数xi, yi，表示第i个炮的位置
     *
     * 输出n行，每行输出一个整数表示第i个炮可以攻击到的炮的数量
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        int[][] node = new int[n][2];
        for (int i = 0; i < n; i++) {
            node[i][0] = scanner.nextInt();
            node[i][1] = scanner.nextInt();
            scanner.nextLine(); 
        }

        for (int i = 0; i < n; i++) {
            int attackedCount = countAttackednode(node, i);
            System.out.println(attackedCount);
        }

    }

    public static int countAttackednode(int[][] node, int attackerIndex) {
        int attackedCount = 0;
        int attackerX = node[attackerIndex][0];
        int attackerY = node[attackerIndex][1];

        attackedCount += checkDirection(node, attackerIndex, 1, 0);
        attackedCount += checkDirection(node, attackerIndex, -1, 0);
        attackedCount += checkDirection(node, attackerIndex, 0, 1);
        attackedCount += checkDirection(node, attackerIndex, 0, -1);

        return attackedCount;
    }

    public static int checkDirection(int[][] node, int attackerIndex, int dx, int dy) {
        int attackerX = node[attackerIndex][0];
        int attackerY = node[attackerIndex][1];
        int cannonCount = node.length;

        int firstCannonX = -1, firstCannonY = -1;
        int secondCannonX = -1, secondCannonY = -1;

        double minDistance1 = Double.MAX_VALUE;
        for (int i = 0; i < cannonCount; i++) {
            if (i == attackerIndex) {
                continue;
            }

            int currentX = node[i][0];
            int currentY = node[i][1];

            if ((dx == 1 && currentX > attackerX && currentY == attackerY) ||
                    (dx == -1 && currentX < attackerX && currentY == attackerY) ||
                    (dy == 1 && currentY > attackerY && currentX == attackerX) ||
                    (dy == -1 && currentY < attackerY && currentX == attackerX)) {

                double distance = Math.abs(currentX - attackerX) + Math.abs(currentY - attackerY);
                if (distance < minDistance1) {
                    minDistance1 = distance;
                    firstCannonX = currentX;
                    firstCannonY = currentY;
                }
            }
        }

        if (firstCannonX == -1) {
            return 0;
        }

        double minDistance2 = Double.MAX_VALUE;
        for (int i = 0; i < cannonCount; i++) {
            if (i == attackerIndex) {
                continue;
            }
            int currentX = node[i][0];
            int currentY = node[i][1];

            if ((dx == 1 && currentX > firstCannonX && currentY == firstCannonY) ||
                    (dx == -1 && currentX < firstCannonX && currentY == firstCannonY) ||
                    (dy == 1 && currentY > firstCannonY && currentX == firstCannonX) ||
                    (dy == -1 && currentY < firstCannonY && currentX == firstCannonX)) {

                double distance = Math.abs(currentX - firstCannonX) + Math.abs(currentY - firstCannonY);
                if (distance < minDistance2) {
                    minDistance2 = distance;
                    secondCannonX = currentX;
                    secondCannonY = currentY;
                }
            }
        }

        if (secondCannonX == -1) {
            return 0;
        }

        return 1;
    }
}