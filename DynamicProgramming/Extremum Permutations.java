import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int l = in.nextInt();
        int[] a = new int[5005];
        int[] b = new int[5005];
        long[][] dp = new long[5005][5005];


        for (int i = 0; i < k; i++) {
            a[in.nextInt()] = 1;
        }

        for (int i = 0; i < l; i++) {
            b[in.nextInt()] = 1;
        }

        for (int i = 1; i < n; i++) {
            if (a[i] == 1 && b[i] == 1){
                System.out.println("0");
                return;
            }
            if ((a[i-1] == 1 && a[i] == 1) || (b[i-1]==1 && b[i] == 1)){
                System.out.println("0");
                return;
            }
        }

        dp[1][1] = 1;
        for (int i = 2; i <= n; i++){
            if (!(a[i-1] == 1  || b[i] == 1)){
                long sum = 0;
                for (int j=1; j <= i; j++){
                    dp[i][j] = add(dp[i][j], sum);
                    sum = add(sum, dp[i-1][j]);
                }
            }
            if (!(b[i-1] == 1 || a[i] == 1)){
                long sum = 0;
                for (int j=i; j>=1; j--){
                    dp[i][j] = add(dp[i][j], sum);
                    sum = add(sum, dp[i-1][j-1]);
                }
            }
        }

        long ans = 0;
        for (int i = 1; i <= n; i++){
            ans = add(ans, dp[n][i]);
        }

        System.out.println(ans);

    }

    private static long add(long x, long v){
        return (x+v) % 1000000007;
    }

}