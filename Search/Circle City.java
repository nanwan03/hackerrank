import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
    	int T = in.nextInt();
    	for(int t = 0; t < T; t++) {
    		double r = in.nextInt();
    		double k = in.nextInt();
    		int count = 0;
    		for(int x = 0; x < Math.sqrt(r); x++) {
    			double y = Math.sqrt(r - x * x);
    			if(Math.abs(Math.round(y) - y) < 1e-6) {
    				count++;
    			}
    		}
    		System.out.println(count <= k / 4 ? "possible" : "impossible");
    	}
    }
}