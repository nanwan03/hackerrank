import java.io.*;
import java.util.*;

public class Solution {

	BufferedReader br;
	PrintWriter out;
	StringTokenizer st;
	boolean eof;

	static class Edge {
		int to, cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

	}

	List<Edge>[] g;

	static final int INF = Integer.MAX_VALUE / 2;

	void solve() throws IOException {
		int n = nextInt();
		int m = nextInt();

		g = new List[n];
		for (int i = 0; i < n; i++) {
			g[i] = new ArrayList<>(0);
		}

		for (int i = 0; i < m; i++) {
			int v1 = nextInt() - 1;
			int v2 = nextInt() - 1;
			int cost = nextInt();
			g[v1].add(new Edge(v2, cost));
			g[v2].add(new Edge(v1, cost));
		}

		final int[] d = new int[n];
		Arrays.fill(d, INF);
		d[0] = 0;
		TreeSet<Integer> set = new TreeSet<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (d[o1] != d[o2]) {
					return Integer.compare(d[o1], d[o2]);
				}
				return o1.compareTo(o2);
			}
		});

		set.add(0);

		while (!set.isEmpty()) {
			int v = set.pollFirst();
			if (v == n - 1)
				break;
			for (int i = 0; i < g[v].size(); i++) {
				Edge e = g[v].get(i);
				if (d[e.to] > Math.max(d[v], e.cost)) {
					set.remove(e.to);
					d[e.to] = Math.max(d[v], e.cost);
					set.add(e.to);
				}
			}
		}

		if (d[n - 1] == INF) {
			out.println("NO PATH EXISTS");
		} else {
			out.println(d[n - 1]);
		}
	}

	Solution() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		new Solution();
	}

	String nextToken() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (Exception e) {
				eof = true;
				return null;
			}
		}
		return st.nextToken();
	}

	String nextString() {
		try {
			return br.readLine();
		} catch (IOException e) {
			eof = true;
			return null;
		}
	}

	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
}