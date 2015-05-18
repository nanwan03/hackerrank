import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Solution     {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";
	static int K, n;
	static boolean[][] g;
	
	static void solve()
	{
		n = ni(); K = ni();
		g = new boolean[n][n];
		for(int i = 0;i < n-1;i++){
			int f = ni()-1, t = ni()-1;
			g[f][t] = g[t][f] = true;
		}
		ret = 1;
		dfs(0, -1);
		out.println(ret);
	}
	
	static long ret;
	
	static long[] dfs(int cur, int pre)
	{
		long[] dp = new long[n+1];
		dp[0] = 1;
		for(int i = 0;i < n;i++){
			if(g[cur][i] && i != pre){
				long[] ldp = dfs(i, cur);
				long[] ndp = new long[n+1];
				for(int j = 0;j <= n;j++){
					if(j+1 <= n)ndp[j+1] += dp[j];
					for(int k = 0;j+k <= n;k++){
						ndp[j+k] += dp[j] * ldp[k];
					}
				}
				dp = ndp;
			}
		}
		long lret = 0;
		for(int i = 0;i <= n;i++){
			if((cur == 0 ? i : i+1) <= K){
				lret += dp[i];
			}
		}
//		tr(cur);
//		tr(lret);
//		tr(dp);
		ret += lret;
		return dp;
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
	
	private static byte[] inbuf = new byte[1024];
	static int lenbuf = 0, ptrbuf = 0;
	
	private static int readByte()
	{
		if(lenbuf == -1)throw new InputMismatchException();
		if(ptrbuf >= lenbuf){
			ptrbuf = 0;
			try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
			if(lenbuf <= 0)return -1;
		}
		return inbuf[ptrbuf++];
	}
	
	private static boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
	private static int ni()
	{
		int num = 0, b;
		boolean minus = false;
		while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if(b == '-'){
			minus = true;
			b = readByte();
		}
		
		while(true){
			if(b >= '0' && b <= '9'){
				num = num * 10 + (b - '0');
			}else{
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
	
	private static void tr(Object... o) { if(INPUT.length() != 0)System.out.println(Arrays.deepToString(o)); }
}
