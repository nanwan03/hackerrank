/* HackerRank Template v0.25 by Sergey Esipenko */
import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.sort;

public class Solution implements Runnable {
	/* START OF SOLUTION */
	
	private static final Random RANDOM = new Random(7777L);
	private static final int MODULO = 1_000_000_000 + 7;
	
	int n;
	int[] array;
	RangeMaxQuery rmq;
	Map<Long, Integer> cache = new HashMap<>();
	
	private void solve() throws IOException  {
		
		n = nextInt();
		array = nextIntArray(n);
		rmq = new RangeMaxQuery(array);
		
//		for (int i = 0; i < n; i++) {
//			for (int j = i; j < n; j++) {
//				System.err.println(i + " " + j + " -> " + count(i, j));
//			}
//		}
//		
		out.println(count(0, n - 1));
	}
	
	private int count(int from, int to) {
		if (from == to) return 1;
		final long index = (((long) from) << 20) | to;
		Integer cached = cache.get(index);
		if (cached == null) {
			int count = 0;
			for (int i = from + 1; i < to; i += 2) {
				if (!testHeap(from + 1, i)) break;
				if (testHeap(i + 1, to)) {
					final int leftCount = count(from + 1, i);
					if (leftCount == 0) break;
					final int add = (int) (leftCount * (long) count(i + 1, to) % MODULO);
					count += add;
					while (count >= MODULO)
						count -= MODULO;
				}
			}
			cache.put(index, cached = count);
		}
		return cached;
	}

	private boolean testHeap(int from, int to) {
		final int root = array[from];
		return root >= rmq.getMaxValue(from + 1, to);
	}

	private static class RangeMaxQuery {
		private final int n;
		private final int[] values;
		
		public RangeMaxQuery(int n) {
			this.n = n;
			this.values = new int [2 * n];
			fill(this.values, Integer.MIN_VALUE);
		}
		
		public RangeMaxQuery(int[] array) {
			this.n = array.length;
			this.values = new int [n + n];
			fill(this.values, Integer.MIN_VALUE);
			System.arraycopy(array, 0, this.values, n, n);
			for (int v = n - 1; v > 0; v--) {
				values[v] = max(values[v + v], values[v + v + 1]);
			}
		}
		
		public int getMaxValue(final int left, final int right) {
			int l = n + left;
			int r = n + right;
			int maxValue = Integer.MIN_VALUE;
			while (l <= r) {
				if ((l & 1) == 1) maxValue = max(maxValue, values[l]);
				if ((r & 1) == 0) maxValue = max(maxValue, values[r]);
				l = (l + 1) >> 1;
				r = (r - 1) >> 1;
			}
			
			return maxValue;
		}
	}
	
	/* END OF SOLUTION */
	/************************************************************************** 
	 * Entry point
	 *************************************************************************/
	
	private static final Solution INSTANCE = new Solution();
	private static final boolean WRITE_LOG = true;
	private static final long STACK_SIZE = 1L << 24; // < 0 for default stack size
	
	private static long initTime;
	private static boolean localRun = false;
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		try {
			initTime = System.currentTimeMillis();
			try {
				localRun = "true".equals(System.getProperty("LOCAL_RUN_7777"));
				if (localRun && new File("input.txt").exists())
					System.setIn(new FileInputStream("input.txt"));
			} catch (SecurityException e) {
				// Can't get property. It seems that solution is running in a secure
				// environment
			}
			if (STACK_SIZE < 0L) {
				INSTANCE.run();
			} else {
				new Thread(null, INSTANCE, "Solver", 1L << 24).start();
			}
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(999);
		}
	}

	@Override
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
			out.close();
			in.close();
			writeLog("Total time: "
					+ (System.currentTimeMillis() - initTime) + " ms");
			writeLog("Memory status: " + memoryStatus());
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(999);
		}
	}
	
	/**************************************************************************
	 * Input 
	 *************************************************************************/
	private BufferedReader in;
	private PrintWriter out;
	private StringTokenizer st = new StringTokenizer("");
	
	private String nextToken() throws IOException {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}
	
	private int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
	
	private long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}
	
	private double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
	
	private int[] nextIntArray(int size) throws IOException {
		int[] ret = new int [size];
		for (int i = 0; i < size; i++)
			ret[i] = nextInt();
		return ret;
	}
	
	private long[] nextLongArray(int size) throws IOException {
		long[] ret = new long [size];
		for (int i = 0; i < size; i++)
			ret[i] = nextLong();
		return ret;
	}
	
	private double[] nextDoubleArray(int size) throws IOException {
		double[] ret = new double [size];
		for (int i = 0; i < size; i++)
			ret[i] = nextDouble();
		return ret;
	}
	
	private String nextLine() throws IOException {
		st = new StringTokenizer("");
		return in.readLine();
	}
	
	private boolean isEof() throws IOException {
		while (!st.hasMoreTokens()) {
			String s = in.readLine();
			if (s == null)
				return true;
			st = new StringTokenizer(s);
		}
		return false;
	}
	
	/************************************************************************** 
	 * Output 
	 *************************************************************************/
	
	private void printIf(final boolean condition, final String msgIfTrue, final String msgIfFalse) {
		out.println(condition ? msgIfTrue : msgIfFalse);
	}
	
	private void printRepeat(String s, int count) {
		for (int i = 0; i < count; i++)
			out.print(s);
	}
	
	private void printArray(int[] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			if (i > 0) out.print(' ');
			out.print(array[i]);
		}
		out.println();
	}
	
	private void printArray(long[] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			if (i > 0) out.print(' ');
			out.print(array[i]);
		}
		out.println();
	}
	
	private void printArray(double[] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			if (i > 0) out.print(' ');
			out.print(array[i]);
		}
		out.println();
	}
	
	private void printArray(double[] array, String spec) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			if (i > 0) out.print(' ');
			out.printf(Locale.US, spec, array[i]);
		}
		out.println();
	}
	
	private void printArray(Object[] array) {
		if (array == null || array.length == 0)
			return;
		boolean blank = false;
		for (Object x : array) {
			if (blank) out.print(' '); else blank = true;
			out.print(x);
		}
		out.println();
	}
	
	@SuppressWarnings("rawtypes")
	private void printCollection(Collection collection) {
		if (collection == null || collection.isEmpty())
			return;
		boolean blank = false;
		for (Object x : collection) {
			if (blank) out.print(' '); else blank = true;
			out.print(x);
		}
		out.println();
	}
	
	/**************************************************************************
	 * Utility
	 *************************************************************************/
	
	private static String memoryStatus() {
		return (Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory() >> 20)
				+ "/" + (Runtime.getRuntime().totalMemory() >> 20) + " MB";
	}
	
	private static void checkMemory() {
		System.err.println(memoryStatus());
	}
	
	private static long getRunningTime() {
		return System.currentTimeMillis() - initTime;
	}
	
	private static void chk(boolean f) {
		if (!f) throw new RuntimeException("Assert failed");
	}
	
	private static void chk(boolean f, String format, Object ... args) {
		if (!f) throw new RuntimeException(String.format(format, args));
	}
	
	private static void writeLog(String format, Object... args) {
		if (localRun && WRITE_LOG)
			System.err.println(String.format(Locale.US, format, args));
	}
	
	private static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	private static void swap(long[] a, int i, int j) {
		long tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	private static void swap(double[] a, int i, int j) {
		double tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	private static void swap(Object[] a, int i, int j) {
		Object tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	private static void shuffle(int[] a, int from, int to) {
		for (int i = from; i <= to; i++)
			swap(a, i, from + RANDOM.nextInt(i - from + 1));
	}
	
	private static void shuffle(long[] a, int from, int to) {
		for (int i = from; i <= to; i++)
			swap(a, i, from + RANDOM.nextInt(i - from + 1));
	}
	
	private static void shuffle(double[] a, int from, int to) {
		for (int i = from; i <= to; i++)
			swap(a, i, from + RANDOM.nextInt(i - from + 1));
	}
	
	private static void shuffle(Object[] a, int from, int to) {
		for (int i = from; i <= to; i++)
			swap(a, i, from + RANDOM.nextInt(i - from + 1));
	}
	
	private static void shuffle(int[] a) {
		if (a == null) return;
		shuffle(a, 0, a.length - 1);
	}
	
	private static void shuffle(long[] a) {
		if (a == null) return;
		shuffle(a, 0, a.length - 1);
	}
	
	private static void shuffle(double[] a) {
		if (a == null) return;
		shuffle(a, 0, a.length - 1);
	}
	
	private static void shuffle(Object[] a) {
		if (a == null) return;
		shuffle(a, 0, a.length - 1);
	}
	
	private static long[] getPartialSums(int[] a) {
		final long[] sums = new long [a.length + 1];
		for (int i = 0; i < a.length; i++)
			sums[i + 1] = sums[i] + a[i];
		return sums;
	}
	
	private static long[] getPartialSums(long[] a) {
		final long[] sums = new long [a.length + 1];
		for (int i = 0; i < a.length; i++)
			sums[i + 1] = sums[i] + a[i];
		return sums;
	}
	
	private static int[] getOrderedSet(int[] a) {
		final int[] set = Arrays.copyOf(a, a.length);
		if (a.length == 0) return set;
		shuffle(set);
		sort(set);
		int k = 1;
		int prev = set[0];
		for (int i = 1; i < a.length; i++) {
			if (prev != set[i]) {
				set[k++] = prev = set[i];
			}
		}
		return Arrays.copyOf(set, k);
	}
	
	private static long[] getOrderedSet(long[] a) {
		final long[] set = Arrays.copyOf(a, a.length);
		if (a.length == 0) return set;
		shuffle(set);
		sort(set);
		int k = 1;
		long prev = set[0];
		for (int i = 1; i < a.length; i++) {
			if (prev != set[i]) {
				set[k++] = prev = set[i];
			}
		}
		return Arrays.copyOf(set, k);
	}
	
	private static int gcd(int x, int y) {
		x = abs(x);
		y = abs(y);
		while (x > 0 && y > 0) {
			if (x > y) {
				x %= y;
			} else {
				y %= x;
			}
		}
		return x + y;
	}
	
	private static long gcd(long x, long y) {
		x = abs(x);
		y = abs(y);
		while (x > 0 && y > 0) {
			if (x > y) {
				x %= y;
			} else {
				y %= x;
			}
		}
		return x + y;
	}
}
