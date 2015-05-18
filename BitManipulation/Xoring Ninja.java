import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Solution {
	static BufferedReader in = new BufferedReader(new InputStreamReader(
			System.in));
	static StringBuilder out = new StringBuilder();
	final static int MODVAL = 1000000007;

	public static void main(String[] args) throws IOException {
		int numT = Integer.parseInt(in.readLine());
		
		for(int t = 0; t < numT; t ++)
		{
			int hasAOne = 0;
			int numVal = Integer.parseInt(in.readLine());
			String line = in.readLine();
			String[] data = line.split("\\s+");
			
			for(int i = 0; i < numVal; i ++)
			{
				hasAOne = Integer.parseInt(data[i]) | hasAOne;
			}
			
			long sum = 0;
			long add = modPow(2,numVal-1);
			
			for(long i = 1; i < 1000000000; i *= 2)
			{
				if((hasAOne & i) > 0)
				{
					sum += add;
					sum %= MODVAL;
				}
				
				add *= 2;
				add %= MODVAL;
			}
			System.out.println(sum);
		}
	}
	
	private static long modPow(long b, int e)
	{
		long res = 1;
		
		while(e > 0)
		{
			if(e%2 == 1)
			{
				res *= b;
				res %= MODVAL;
			}
			b *= b;
			b %= MODVAL;
			e /= 2;
		}
		
		return res;
	}
}
