import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {

	static class SegmentTree {
		static final int UNIT = Integer.MIN_VALUE;
		int[] max, input;
		int M;

		public SegmentTree(int[] input) {
			this.input = input;
			for (M = 1; M < 10 + input.length; M *= 2)
				;
			max = new int[2 * M];
			for (int i = M; i < max.length; i++)
				if (i - M < input.length)
					max[i] = input[i - M];
				else
					max[i] = UNIT;
			for (int i = M / 2; i >= 1; i /= 2)
				for (int j = i; j < 2 * i; j++)
					max[j] = Math.max(max[2 * j], max[2 * j + 1]);
		}
        
		public void plus(int position, int delta) {
			max[M + position] += delta;
			for (int i = (M + position) / 2; i >= 1; i /= 2)
				max[i] = Math.max(max[2 * i], max[2 * i + 1]);
		}
	}

	static class Interval {
		int left;
		int right;
		int index;

		public Interval(int left, int right, int index) {
			this.left = left;
			this.right = right;
			this.index = index;
		}

		static class Cmp implements Comparator<Interval> {
			int sqrtN;

			public Cmp(int n) {
				sqrtN = Math.max(1, (int) Math.sqrt(n));
			}

			@Override
			public int compare(Interval a, Interval b) {
				if (a.left / sqrtN < b.left / sqrtN)
					return -1;
				else if (a.left / sqrtN > b.left / sqrtN)
					return 1;
				else if (a.right < b.right)
					return -1;
				else if (a.right > b.right)
					return 1;
				else
					return 0;
			}

		}
	}

	static class Point implements Comparable<Point> {
		int x;
		int y;
		int f;

		public Point(int x, int y, int f) {
			this.x = x;
			this.y = y;
			this.f = f;
		}

		@Override
		public int compareTo(Point other) {
			return ((Integer) y).compareTo(other.y);
		}
	}

	static int N, Q;
	static Point points[];
	static int sortedF[];

	public static void main(String[] args) throws Exception {
		Reader.init(System.in);
		BufferedWriter cout = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Reader.nextInt();
		Q = Reader.nextInt();
		Reader.nextInt(); // ignore V

		points = new Point[N];
		sortedF = new int[N];
		for (int i = 0; i < N; i++) {
			points[i] = new Point(Reader.nextInt(), Reader.nextInt(), Reader.nextInt());
			sortedF[i] = points[i].f;
		}
		Arrays.sort(sortedF);
		Arrays.sort(points);
		for (Point point : points)
			point.f = Arrays.binarySearch(sortedF, point.f);

		Interval[] queries = new Interval[Q];
		int ans[] = new int[Q];
		for (int i = 0; i < Q; i++) {
			int up = Reader.nextInt(), down = Reader.nextInt();
			Reader.next(); // ignore T
			int left = find2(points, down);
			int right = find1(points, up);
			queries[i] = new Interval(left, right, i);
		}
		Arrays.sort(queries, new Interval.Cmp(N));

		SegmentTree tree = new SegmentTree(new int[N]);
		int preLeft = -1, preRight = -1;
		for (Interval query : queries) {
			if (query.left > query.right)
				continue;
			if (preLeft == -1) {
				for (int i = query.left; i <= query.right; i++)
					tree.plus(points[i].f, 1);
			} else {
				if (query.left < preLeft) {
					for (int i = query.left; i < preLeft; i++)
						tree.plus(points[i].f, 1);
				} else {
					// query.left >= preLeft
					for (int i = preLeft; i < query.left; i++)
						tree.plus(points[i].f, -1);
				}

				if (query.right < preRight) {
					for (int i = preRight; i > query.right; i--)
						tree.plus(points[i].f, -1);
				} else {
					// query.right >= preRight
					for (int i = query.right; i > preRight; i--)
						tree.plus(points[i].f, 1);
				}
			}

			ans[query.index] = tree.max[1];
			preLeft = query.left;
			preRight = query.right;
		}

		for (int i : ans)
			cout.write(String.format("%d%n", i));

		cout.close();
	}

	/**
	 * Find the largest i s.t. points[i].y <= up
	 */
	static int find1(Point points[], int up) {
		int left = -1, right = points.length;
		// left <= up
		// right > up
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (points[mid].y <= up)
				left = mid;
			else
				right = mid;
		}
		return left;
	}

	/**
	 * Find the smallest i s.t. points[i].y >= down
	 */
	static int find2(Point points[], int down) {
		int left = -1, right = points.length;
		// left < up
		// right >= up
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (points[mid].y < down)
				left = mid;
			else
				right = mid;
		}
		return right;
	}

	/** Class for buffered reading int and double values */
	static class Reader {
		static BufferedReader reader;
		static StringTokenizer tokenizer;

		/** call this method to initialize reader for InputStream */
		static void init(InputStream input) {
			reader = new BufferedReader(new InputStreamReader(input));
			tokenizer = new StringTokenizer("");
		}

		/** get next word */
		static String next() throws IOException {
			while (!tokenizer.hasMoreTokens()) {
				// TODO add check for eof if necessary
				tokenizer = new StringTokenizer(reader.readLine());
			}
			return tokenizer.nextToken();
		}

		static int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		static double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}
	}
}
