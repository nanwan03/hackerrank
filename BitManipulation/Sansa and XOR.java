import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int testcase = in.nextInt();
        for (int i = 0; i < testcase; i++) {
            int size = in.nextInt();
            int[] input = new int[size];
            for (int j = 0; j < size; j++) {
            	input[j] = in.nextInt();
            }
            if (size % 2 == 0) {
            	System.out.println("0");
            } else {
            	int temp = 0;
                for (int j = 0; j < size; j = j + 2) {
                    temp = temp ^ input[j];
                }
            	System.out.println(temp);
            }
        }
    }
}