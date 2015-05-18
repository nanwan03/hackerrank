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
            int size = Integer.parseInt(in.nextLine());
            char[][] input = new char[size][size];
            for (int j = 0; j < size; j++) {
                input[j] = in.nextLine().toCharArray();
                Arrays.sort(input[j]);
            }
            solve(input);
        }
    }
    private static void solve(char[][] input) {
        int size = input.length;
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size; j++) {
                if (input[j - 1][i] > input[j][i]) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }
}