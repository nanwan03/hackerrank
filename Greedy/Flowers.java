/* Sample program illustrating input/output methods */
import java.util.*;

class Solution{
   public static void main( String args[] ){
      
// helpers for input/output      

      Scanner in = new Scanner(System.in);
       int flowers = in.nextInt();
       int people = in.nextInt();
       int[] prices = new int[flowers];
       for (int i = 0; i < flowers; i++) {
           prices[i] = in.nextInt();
       }
       Arrays.sort(prices);
       in.close();
       solve(prices, people);
   }
    private static void solve(int[] prices, int people) {
        int rst = 0;
        int flowers = prices.length - 1;
        int level = 0;
        while (flowers >= 0) {
            level++;
            for (int i = 0; flowers >= 0 && i < people; i++) {
                rst = rst + prices[flowers--] * (level);
            }
        }
        System.out.println(rst);
    }
}
