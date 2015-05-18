import java.io.*;
import java.util.Arrays;

public class Solution {

    static class Event implements Comparable<Event> {
        int x;
        long add;

        Event(int x, long add) {
            this.x = x;
            this.add = add;
        }

        @Override
        public int compareTo(Event o) {
            return x - o.x;
        }
    }

    public static void solve(Input in, PrintWriter out) throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        Event[] es = new Event[2 * m];
        for (int i = 0; i < m; ++i) {
            int a = in.nextInt();
            int b = in.nextInt();
            long add = in.nextLong();
            es[2 * i + 0] = new Event(a, add);
            es[2 * i + 1] = new Event(b + 1, -add);
        }
        Arrays.sort(es);
        long ans = Long.MIN_VALUE;
        long cur = 0;
        for (int i = 0; i < es.length; ) {
            if (es[i].x > n) {
                break;
            }
            int j = i;
            while (j < es.length && es[i].x == es[j].x) {
                cur += es[j++].add;
            }
            ans = Math.max(ans, cur);
            i = j;
        }
        out.println(ans);
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
