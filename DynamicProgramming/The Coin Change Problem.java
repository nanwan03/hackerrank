import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        int cents = in.nextInt();
        int coins = in.nextInt();
        int[] values = new int[coins];
        for(int i = 0;i<coins;i++){
            values[i]=in.nextInt();
      
        }
        
        long[][]dp = new long[cents+1][coins+1];
        dp[0][0]=1;
        for(int coin = 1;coin<coins+1;coin++){
            for(int cent = 0;cent<cents+1;cent++){
                if(values[coin-1] >cent)
                    dp[cent][coin]=dp[cent][coin-1];
                else
                    dp[cent][coin]=dp[cent][coin-1]+dp[cent-values[coin-1]][coin];
            }
        }
        System.out.println(dp[cents][coins]);
        
        
        
    }
}