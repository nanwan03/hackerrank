import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int testcase = Integer.parseInt(in.nextLine());
        for (int i = 0; i < testcase; i++) {
            int size = in.nextInt();
            int target = in.nextInt();
            in.nextLine();
            int[] input = new int[size];
            for (int j = 0; j < size; j++) {
               input[j] = in.nextInt();
            }
            if (i < testcase - 1) {
                in.nextLine();
            }
            System.out.println(solve(input, target));
        }
    }
    private static long solve(int[] input, int target) {
        long size = (long)(input.length);
        long total = size * (size + 1) / 2;
        long start = 0;
        long length = 0;
        for (long i = 0; i <= input.length; i++) {
            if (i == input.length || input[(int)i] > target) {
                length = i - start;
                if (length > 0) {
                    total -= length * (length + 1) / 2;
                }
                start = i + 1;
            }
        }
        return total;
    }
}