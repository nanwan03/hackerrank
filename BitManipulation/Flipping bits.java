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
            long input = in.nextLong();
            long output = (~input) & 0x0FFFFFFFFl;
            System.out.println(output);
        }
    }
}