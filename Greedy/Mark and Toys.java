import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] inputs = in.nextLine().split("\\s+");
        int toyNo = Integer.parseInt(inputs[0]);
        int totalMoney = Integer.parseInt(inputs[1]);
        int[] prices = new int[toyNo];
        for (int i = 0; i < toyNo; i++) {
            prices[i] = in.nextInt();
        }
        Arrays.sort(prices);
        int rst = 0;
        for (int i = 0; i < toyNo && prices[i] <= totalMoney; i++) {
            rst++;
            totalMoney -= prices[i];
        }
        System.out.println(rst);
    }
}
