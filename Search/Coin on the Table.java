import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();
        int k = in.nextInt();
        int targetR = -1;
        int targetC = -1;
        in.nextLine();
        char[][] table = new char[row][col];
        for (int i = 0; i < row; i++) {
            table[i] = in.nextLine().toCharArray();
            for (int j = 0; j < col; j++) {
                if (table[i][j] == '*') {
                    targetR = i;
                    targetC = j;
                }
            }
        }
        int[][] dp = new int[row][col];
        for (int[] v : dp) {
            Arrays.fill(v, Integer.MAX_VALUE);
        }
        Set<Integer> currentLevel = new HashSet<Integer>();
        currentLevel.add(0);
        dp[0][0] = 0;
        for (int step = 0; step < k; step++) {
            Set<Integer> next = new HashSet<Integer>();
            for (int cur : currentLevel) {
                int r = cur / col;
                int c = cur % col;
                if (r > 0) {
                    update(dp, r - 1, c, r, c, table[r][c] == 'U' ? 0 : 1, next, col);
                }
                if (r + 1 < row) {
                    update(dp, r + 1, c, r, c, table[r][c] == 'D' ? 0 : 1, next, col);
                }
                if (c > 0) {
                    update(dp, r, c - 1, r, c, table[r][c] == 'L' ? 0 : 1, next, col);
                }
                if (c + 1 < col) {
                    update(dp, r, c + 1, r, c, table[r][c] == 'R' ? 0 : 1, next, col);
                }
            }
            currentLevel = next;
        }
        System.out.println(dp[targetR][targetC] == Integer.MAX_VALUE ? -1 : dp[targetR][targetC]);
    }
    private static void update(int[][] dp, int r, int c, int row, int col, int v, Set<Integer> set, int totalCol) {
        if (dp[r][c] > dp[row][col] + v) {
            dp[r][c] = dp[row][col] + v;
            set.add(r * totalCol + c);
        }
    }
}