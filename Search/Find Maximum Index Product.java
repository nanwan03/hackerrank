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
        long product = 0;
        for (int i = 1; i < size - 1; i++) {
            if (input[i] < input[i - 1] && input[i] < input[i + 1]) {
                product = Math.max(product, (long)(i) * (long)(i + 2));
            }
        }
        System.out.println(product);
    }
}