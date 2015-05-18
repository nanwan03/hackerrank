import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int input = in.nextInt();
        System.out.println(solution(input));
    }
    private static int solution(int input) {
        return getSumFactor(input) == getSum(input) ? 1 : 0;
    }
    private static int getSum(int input) {
        int sum = 0;
        while (input != 0) {
            sum += input % 10;
            input /= 10;
        }
        return sum;
    }
    private static int getSumFactor(int input) {
        int sum = 0;
        for (int i = 2; i <= input / i; i++) {
            while (input % i == 0) {
                sum += getSum(i);
                input /= i;
            }
        }
        if (input > 1) {
            sum += getSum(input);
        }
        return sum;
    }
}