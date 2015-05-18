import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

   public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		 Scanner stdin=new Scanner(System.in);
	   //  stdin = new Scanner(new FileInputStream("in.txt"));
	     int nCase = stdin.nextInt(); 
	     while(nCase-- > 0) {
	    	 int num = stdin.nextInt();
	    	 long m = stdin.nextLong();
	    	 long n[] = new long[num];
	    	 for(int i = 0; i < num; i++)
	    		 n[i] = stdin.nextLong();
	    	 System.out.println(getMaxMod3(n, m));
	     }
	}
	
	static class Pair{
		long sum;
		int index;
		Pair(){}
		
		Pair(int index, long sum) {
			this.index = index;
			this.sum = sum;
		}
	}
	
	/**
	 * Sorting the accumulate sum
	 * O(nlogn)
	 * */
	static public long getMaxMod3(long n[], long m) {
		Pair p[] = new Pair [n.length];
		p[0] = new Pair(0, n[0] % m);
		long maxSum  = n[0] % m;
		for(int i = 1; i < n.length; i++) {
			p[i] = new Pair(i, (n[i] + p[i - 1].sum) % m);
			maxSum = Math.max(maxSum, p[i].sum);
		} 
		Arrays.sort(p, new Comparator<Pair> (){

			public int compare(Pair p0, Pair p1) {
				// TODO Auto-generated method stub
				long d = p0.sum - p1.sum;
				if(d > 0) 	return 1;
				else if(d < 0)	return -1;
				else return 0;
			}
			
		});
		for(int i = 0; i < n.length - 1; i++) {
			if(p[i].index > p[i + 1].index)
				maxSum = Math.max(maxSum, p[i].sum - p[i + 1].sum + m);
		} 
			
		return maxSum;
	}
}