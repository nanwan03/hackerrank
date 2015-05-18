import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class Solution {	
	BufferedReader reader;
    StringTokenizer tokenizer;
    PrintWriter out;
    
	public void solve() throws IOException {				
		int T = nextInt();
		for (int t = 0; t < T; t++) {
			int N = nextInt();
			boolean[][] map = new boolean[2][N];
			String line1 = reader.readLine();
			String line2 = reader.readLine();
			for (int i = 0; i < N; i++) {
				map[0][i] = line1.charAt(i) == '1';
				map[1][i] = line2.charAt(i) == '1';
			}
			if (pack(map)) {
				out.println("YES");
			} else {
				out.println("NO");
			}
		}
	}
	
	public boolean pack(boolean[][] map) {
		boolean done = true;
		for (int i = 0; i < map[0].length; i++) {
			if (!map[0][i] || !map[1][i]) {
				done = false;
				break;
			}
		}
		
		if (done) return true;
				
		for (int i = 0; i < map[0].length; i++) {
			if (!map[0][i]) {
				if (!map[1][i]) {
					map[0][i] = true;
					map[1][i] = true;
					done = done || pack(map);
					map[0][i] = false;
					map[1][i] = false;
				}
				
				if (i < map[0].length - 1 && !map[0][i+1]) {
					map[0][i] = true;
					map[0][i+1] = true;
					done = done || pack(map);
					map[0][i] = false;
					map[0][i+1] = false;
				}
				break;
			}
			
			if (!map[1][i]) {
				if (i < map[0].length - 1 && !map[0][i+1]) {
					map[1][i] = true;
					map[0][i+1] = true;
					done = done || pack(map);
					map[1][i] = false;
					map[0][i+1] = false;
				}
				if (i < map[0].length - 1 && !map[1][i+1]) {
					map[1][i] = true;
					map[1][i+1] = true;
					done = done || pack(map);
					map[1][i] = false;
					map[1][i+1] = false;
				}
				break;
			}
			
		}
		return done;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Solution().run();
	}
	
	public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
            out = new PrintWriter(System.out);
            solve();
            reader.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
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

    String nextToken() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

}
