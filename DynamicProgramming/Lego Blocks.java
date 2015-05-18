import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class Solution {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";
	static int mod = 1000000007;
	
	static void solve()
	{
		long[] dp = new long[1001];
		dp[0] = 1;
		for(int i = 1;i <= 1000;i++){
			for(int j = 1;j <= 4 && i-j >= 0;j++){
				dp[i] += dp[i-j];
			}
			dp[i] %= mod;
		}
		
		for(int T = ni();T >= 1;T--){
			int h = ni(), w = ni();
			long[] valid = new long[w+1];
			long[] all = new long[w+1];
			for(int i = 1;i <= w;i++){
				all[i] = modpow(dp[i], h, mod);
				long inval = 0;
				for(int j = 1;j < i;j++){
					inval += valid[j]*all[i-j]%mod;
				}
				valid[i] = ((all[i]-inval)%mod+mod)%mod;
			}
			out.println(valid[w]);
		}
	}
	
	public static long modpow(long a, long n, long mod)
	{
		long ret = 1;
		long mul = a;
		for(;n > 0;n >>>= 1){
			if((n&1)==1){
				ret = (ret * mul) % mod;
			}
			mul = (mul * mul) % mod;
		}
		return ret;
	}
	
	public static void main(String[] args) throws Exception
	{
		long S = System.currentTimeMillis();
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);
		
		solve();
		out.flush();
		long G = System.currentTimeMillis();
		tr(G-S+"ms");
	}
	
	static boolean eof()
	{
		try {
			is.mark(1000);
			int b;
			while((b = is.read()) != -1 && !(b >= 33 && b <= 126));
			is.reset();
			return b == -1;
		} catch (IOException e) {
			return true;
		}
	}
		
	static int ni()
	{
		try {
			int num = 0;
			boolean minus = false;
			while((num = is.read()) != -1 && !((num >= '0' && num <= '9') || num == '-'));
			if(num == '-'){
				num = 0;
				minus = true;
			}else{
				num -= '0';
			}
			
			while(true){
				int b = is.read();
				if(b >= '0' && b <= '9'){
					num = num * 10 + (b - '0');
				}else{
					return minus ? -num : num;
				}
			}
		} catch (IOException e) {
		}
		return -1;
	}
	
	static void tr(Object... o) { if(INPUT.length() != 0)System.out.println(Arrays.deepToString(o)); }
}
