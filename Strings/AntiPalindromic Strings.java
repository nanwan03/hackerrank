import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static final long MOD = (long)Math.pow(10, 9) + 7;
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
    	int testcase = in.nextInt();
    	for (int i = 0; i < testcase; i++) {
    		long n = in.nextLong();
    		long m = in.nextLong();
    		System.out.println(solution(n, m));
    	}
    }
    private static long solution(long n, long m) {
    	if (n == 1L) {
    		return m;
    	} else {
    		long rst = (m *  (m - 1)) % MOD;
    		long power = pow(m - 2, n -2) % MOD;
    		return (rst * power) % MOD;
    	}
    }
    private static long pow (long m, long n) {
    	if (n == 0) {
    		return 1L;
    	} else if (n == 1) {
    		return m;
    	}
    	long r1 = pow(m, n / 2) % MOD;
    	long r2 = (n % 2 == 1) ? m : 1L;
    	return ((r1 * r1) % MOD * r2) % MOD; 
    }
}