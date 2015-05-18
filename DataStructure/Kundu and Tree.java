import java.io.*;
import java.util.ArrayList;

public class Solution {

    public static void solve(Input in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        ArrayList<Integer>[] edges = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            edges[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n - 1; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            if (in.next().equals("b")) {
                edges[a].add(b);
                edges[b].add(a);
            }
        }
        boolean[] col = new boolean[n];
        long c1 = 0, c2 = 0, c3 = 0;
        for (int i = 0; i < n; ++i) {
            if (!col[i]) {
                int c = dfs(i, edges, col);
                c3 += c * c2;
                c2 += c * c1;
                c1 += c;
            }
        }
        out.println(c3 % 1000000007);
    }

    private static int dfs(int i, ArrayList<Integer>[] edges, boolean[] col) {
        if (col[i]) {
            return 0;
        }
        col[i] = true;
        int r = 1;
        for (int j : edges[i]) {
            r += dfs(j, edges, col);
        }
        return r;
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(System.out);
        solve(new Input(new BufferedReader(new InputStreamReader(System.in))), out);
        out.close();
    }

    static class Input {
        BufferedReader in;
        StringBuilder sb = new StringBuilder();

        public Input(BufferedReader in) {
            this.in = in;
        }

        public Input(String s) {
            this.in = new BufferedReader(new StringReader(s));
        }

        public String next() throws IOException {
            sb.setLength(0);
            while (true) {
                int c = in.read();
                if (c == -1) {
                    return null;
                }
                if (" \n\r\t".indexOf(c) == -1) {
                    sb.append((char)c);
                    break;
                }
            }
            while (true) {
                int c = in.read();
                if (c == -1 || " \n\r\t".indexOf(c) != -1) {
                    break;
                }
                sb.append((char)c);
            }
            return sb.toString();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}
