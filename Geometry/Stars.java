/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/

//circle
//have an angle
//subtract the weight to the right from the weight to the left

import java.util.*;

public class Solution {
    static  long x[],y[],w[];
    
    static long minLine(int i,int j)
	{
		long w1=0,w2=0;
		
		//Ax1+By1=Ax2+By2
		long A=y[j]-y[i];
		long B=x[i]-x[j];
		long C=A*x[i]+B*y[i];
		
		for(int k=0;k<x.length;k++)
		{
			if(A*x[k]+B*y[k]>C)
				w1+=w[k];
			else if(A*x[k]+B*y[k]<C)
				w2+=w[k];
		}
		
		long imax=Math.max(w[i],w[j]),imin=Math.min(w[i],w[j]);
		
		long wtry11=w1+imax,wtry21=w2+imin;
		long wtry12=w1+imin,wtry22=w2+imax;
		
		return Math.max(Math.min(wtry11,wtry21),Math.min(wtry12,wtry22));
	}
	
	public static void main(String args[]) throws Exception
	{
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		
		x=new long[n];
		y=new long[n];
		w=new long[n];
		
		for(int i=0;i<n;i++)
		{
			x[i]=in.nextInt();
			y[i]=in.nextInt();
			w[i]=in.nextInt();
		}
		
		long max=Integer.MIN_VALUE;
		
		for(int i=0;i<n;i++)
		{
			for(int j=i+1;j<n;j++)
			{
				long val=minLine(i,j);
				if(val>max)
					max=val;
			}
		}
		System.out.println(max);
	}
}