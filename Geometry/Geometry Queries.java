import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Solution {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";
	
	static void solve()
	{
		int n = ni();
		int[][] lines = new int[n][];
		for(int i = 0;i < n;i++){
			lines[i] = na(2);
			lines[i][0] = -lines[i][0];
			lines[i][1] = -lines[i][1];
		}
		SegmentTreeSeqEnvelope st = new SegmentTreeSeqEnvelope(lines);
		
		int Q = ni();
		for(int i = 0;i < Q;i++){
			int L = ni()-1, R = ni()-1;
			long x = ni(), y = ni();
			long sy = -st.query(L, R+1, x);
			out.println(y < sy ? "YES" : "NO");
		}
	}
	
	public static class SegmentTreeSeqEnvelope {
		public int M, H, N;
		public int[][][] lines; // [pos]->num
		public Envelope[] envs;
		
		public SegmentTreeSeqEnvelope(int[][] a)
		{
			N = a.length;
			M = Integer.highestOneBit(Math.max(N-1, 1))<<2;
			H = M>>>1;
			
			lines = new int[M][][];
			envs = new Envelope[M];
			for(int i = 0;i < N;i++){
				lines[H+i] = new int[][]{a[i]};
			}
			for(int i = H-1;i >= 1;i--){
				lines[i] = mergeNaive(lines[2*i], lines[2*i+1]);
			}
			for(int i = M-1;i >= 1;i--){
				if(lines[i] != null){
					envs[i] = new Envelope(lines[i].length);
					for(int[] line : lines[i]){
						envs[i].add(line[0], line[1]);
					}
				}
			}
		}
		
		private int[][] mergeNaive(int[][] L, int[][] R)
		{
			if(L == null)return null;
			if(R == null)return Arrays.copyOf(L, L.length);
			int[][] C = new int[L.length+R.length][];
			int p = 0, q = 0, r = 0;
			while(p < L.length && q < R.length){
				if(L[p][0] >= R[q][0]){
					C[r++] = L[p++];
				}else{
					C[r++] = R[q++];
				}
			}
			while(p < L.length)C[r++] = L[p++];
			while(q < R.length)C[r++] = R[q++];
			if(r != L.length+R.length)throw new AssertionError(r + " " +L.length + " " + R.length);
			return C;
		}
		
		public long gy;
		
		public long query(int l, int r, long x){ gy = Long.MAX_VALUE; query(l, r, x, 0, H, 1); return gy;}
		
		private void query(int l, int r, long x, int cl, int cr, int cur)
		{
			if(lines[cur] == null)return;
			if(l <= cl && cr <= r){
				gy = Math.min(gy, envs[cur].query(x));
			}else{
				int mid = cl+cr>>>1;
				if(cl < r && l < mid){
					query(l, r, x, cl, mid, 2*cur);
				}
				if(mid < r && l < cr){
					query(l, r, x, mid, cr, 2*cur+1);
				}
			}
		}
	}
	
	public static class Envelope {
		public static final long INF = Integer.MIN_VALUE;
		
		public long[] xs;
		public int[] intercepts, slopes;
		public int p;
		
		public Envelope(int n)
		{
			xs = new long[n];
			intercepts = new int[n];
			slopes = new int[n];
			p = 0;
		}
		
		public void add(int slope, int intercept)
		{
			for(int i = p-1;i >= 0;i--){
				if(intercept+xs[i]*slope <= intercepts[i]+xs[i]*slopes[i]){ // x=xs[i]
					p--;
					continue;
				}
				if(slope == slopes[i]){
					if(intercept >= intercepts[i]){
						return;
					}else{
						continue;
					}
				}
				// y=sx+i vs y=Sx+I
				// sx+i=Sx+I
				// x=(i-I)/(S-s)
				long num = intercept-intercepts[i];
				long den = slopes[i]-slope;
				long nx = num < 0 ? num/den : (num+den-1)/den;
				xs[p] = nx;
				intercepts[p] = intercept;
				slopes[p] = slope;
				p++;
				return;
			}
			
			xs[p] = INF;
			intercepts[p] = intercept;
			slopes[p] = slope;
			p++;
		}
		
		public long query(long x)
		{
			int ind = Arrays.binarySearch(xs, 0, p, x);
			if(ind < 0)ind = -ind-2;
			return slopes[ind]*x + intercepts[ind];
		}
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
	
	private static boolean eof()
	{
		if(lenbuf == -1)return true;
		int lptr = ptrbuf;
		while(lptr < lenbuf)if(!isSpaceChar(inbuf[lptr++]))return false;
		
		try {
			is.mark(1000);
			while(true){
				int b = is.read();
				if(b == -1){
					is.reset();
					return true;
				}else if(!isSpaceChar(b)){
					is.reset();
					return false;
				}
			}
		} catch (IOException e) {
			return true;
		}
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
	private static int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }
	
	private static double nd() { return Double.parseDouble(ns()); }
	private static char nc() { return (char)skip(); }
	
	private static String ns()
	{
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while(!(isSpaceChar(b))){ // when nextLine, (isSpaceChar(b) && b != ' ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
	
	private static char[] ns(int n)
	{
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while(p < n && !(isSpaceChar(b))){
			buf[p++] = (char)b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}
	
	private static char[][] nm(int n, int m)
	{
		char[][] map = new char[n][];
		for(int i = 0;i < n;i++)map[i] = ns(m);
		return map;
	}
	
	private static int[] na(int n)
	{
		int[] a = new int[n];
		for(int i = 0;i < n;i++)a[i] = ni();
		return a;
	}
	
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
	
	private static long nl()
	{
		long num = 0;
		int b;
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
