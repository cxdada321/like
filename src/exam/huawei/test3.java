package exam.huawei;

import java.util.*;

public class test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> cur = new Stack<>();
        Stack<Integer> next = new Stack<>();
        int n = sc.nextInt();
        sc.nextLine();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] line = sc.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }

        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        int dir = 0;
        int count = 0;
        while (top <= bottom && left <= right) {
            if (dir == 0) {
                for (int i = left; i <= right; i++) {
                    int num = matrix[top][i];
                    if (cur.isEmpty()) {
                        cur.push(num);
                    } else if (cur.peek() < num) {
                        cur.push(num);
                    } else {
                        while (true) {
                            next.push(cur.pop());
                            count++;
                            if (cur.isEmpty() || cur.peek() < num) {
                                cur.push(num);
                                break;
                            }
                        }
                        while (!next.isEmpty()) {
                            cur.push(next.pop());
                        }
                    }
                }
                top++;
            } else if (dir == 1) {
                for (int i = top; i <= bottom; i++) {
                    int num = matrix[i][right];
                    if (cur.isEmpty()) {
                        cur.push(num);
                    } else if (cur.peek() < num) {
                        cur.push(num);
                    } else {
                        while (true) {
                            next.push(cur.pop());
                            count++;
                            if (cur.isEmpty() || cur.peek() < num) {
                                cur.push(num);
                                break;
                            }
                        }
                        while (!next.isEmpty()) {
                            cur.push(next.pop());
                        }
                    }
                }
                right--;
            } else if (dir == 2) {
                for (int i = right; i >= left; i--) {
                    int num = matrix[bottom][i];
                    if (cur.isEmpty()) {
                        cur.push(num);
                    } else if (cur.peek() < num) {
                        cur.push(num);
                    } else {
                        while (true) {
                            next.push(cur.pop());
                            count++;
                            if (cur.isEmpty() || cur.peek() < num) {
                                cur.push(num);
                                break;
                            }
                        }
                        while (!next.isEmpty()) {
                            cur.push(next.pop());
                        }
                    }
                }
                bottom--;
            } else if (dir == 3) {
                for (int i = bottom; i >= top; i--) {
                    int num = matrix[i][left];
                    if (cur.isEmpty()) {
                        cur.push(num);
                    } else if (cur.peek() < num) {
                        cur.push(num);
                    } else {
                        while (true) {
                            next.push(cur.pop());
                            count++;
                            if (cur.isEmpty() || cur.peek() < num) {
                                cur.push(num);
                                break;
                            }
                        }
                        while (!next.isEmpty()) {
                            cur.push(next.pop());
                        }
                    }
                }
                left++;
            }
            dir = (dir + 1) % 4;
        }
        System.out.println(count % 1000000007);
    }
}
