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
    		long num = Long.parseLong(in.nextLine());
    		System.out.println(solution(num));
    	}
    }
    private static String solution(long num) {
    	return isFibo(Long.toString(num)) ? "IsFibo" : "IsNotFibo";
    }
    private static boolean isFibo(String num) {
    	BigInteger input1 = new BigInteger(num).pow(2).multiply(new BigInteger("5"));
    	BigInteger input2 = new BigInteger("4");
    	return isSquare(input1.add(input2)) || 
    			isSquare(input1.subtract(input2));
    }
    private static boolean isSquare(BigInteger bInt) {
    	BigInteger y;
    	BigInteger two = BigInteger.valueOf(2L);
    	for (y = bInt.divide(two);
                y.compareTo(bInt.divide(y)) > 0;
                y = ((bInt.divide(y)).add(y)).divide(two));
        return y.pow(2).compareTo(bInt) == 0;
    }
}