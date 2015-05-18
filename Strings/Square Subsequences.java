import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static final long MOD = 1000000007L;
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int testcase = Integer.parseInt(in.nextLine());
        for (int i = 0; i < testcase; i++) {
            System.out.println(solution(in.nextLine()));
        }
    }
    private static long solution(String str) {
        long rst = 0;
        for (int i = 1; i < str.length(); i++) {
            rst += help(str.substring(i), str.substring(0, i));
            rst %= MOD;
        }
        return rst;
    }
    private static long help(String str1, String str2) {
        long[][] dp = new long[str1.length()][str2.length()];
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                dp[i][j] = 0;
            }
        }
        for (int i = 0; i < str2.length(); i++) {
            if (str1.charAt(0) == str2.charAt(i)) {
                dp[0][i] = 1L;
            }
            if (i > 0) {
                dp[0][i] += dp[0][i - 1];
            }
            dp[0][i] %= MOD;
        }
        for (int i = 1; i < str1.length(); i++) {
            dp[i][0] = dp[i - 1][0];
            for (int j = 1; j < str2.length(); j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1]) % MOD;
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
                dp[i][j] %= MOD;
            }
        }
        return (dp[str1.length() - 1][str2.length() - 1]) % MOD;
    }
}