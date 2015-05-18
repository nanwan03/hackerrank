import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int size = Integer.parseInt(in.nextLine());
        int[] weight = new int[size];
        for (int i = 0; i < size; i++) {
            weight[i] = in.nextInt();
        }
        Arrays.sort(weight);
        solve(weight);
    }
    private static void solve(int[] weight) {
        int rst = 0;
        for (int i = 0; i < weight.length; i++) {
            rst++;
            int orig = weight[i];
            while (i + 1 < weight.length && weight[i + 1] <= orig + 4) {
                i++;
            }
        }
        System.out.println(rst);
    }
}