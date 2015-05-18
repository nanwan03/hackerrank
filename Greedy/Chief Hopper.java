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
        int[] input = new int[size];
        for (int i = 0; i < size; i++) {
            input[i] = in.nextInt();
        }
        solve(input);
    }
    private static void solve(int[] input) {
        int rst = 0;
        for (int i = input.length - 1; i >= 0; i--) {
            rst = calculate(input[i], rst);
        }
        System.out.println(rst);
    }
    private static int calculate(int height, int minReq) {
        int temp = minReq + height;
        if (temp % 2 == 0) {
            return (temp / 2);
        } else {
            return (temp + 1) / 2;
        }
        
    }
}