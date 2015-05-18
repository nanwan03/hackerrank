import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class Solution {
	static class Foo53 {
		int N;
		int[] arr;
		void main() {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(System.in));
				String s = br.readLine();
				N = Integer.parseInt(s.trim());
				arr = new int[N];
				String[] parts = br.readLine().trim().split(" ");
				for (int i = 0; i < N; i++)
					arr[i] = Integer.parseInt(parts[i].trim());
				int res = foo();
				System.out.println(res);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if (br != null) {
					try { br.close(); } catch (Exception e) { }
				}
			}
		}
		boolean ok(int K) {
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int val : arr) {
				val &= ~((1<<K)-1);
				if (!map.containsKey(val)) {
					map.put(val, 1);
				} else {
					map.put(val, map.get(val)+1);
				}
			}
			int max = 0;
			for (int count : map.values()) {
				max = max(max, count);
			}
			return max <= N/2;
		}
		int foo() {
			int lo = 0, hi = 30;
			while (lo < hi) {
				int mid = lo + (hi-lo+1)/2;
				if (!ok(mid))
					hi = mid-1;
				else
					lo = mid;
			}
			if (ok(lo))
				return lo;
			return -1;
		}
	}
	public static void main(String[] args) {
		Foo53 foo = new Foo53();
		foo.main();
	}
}
