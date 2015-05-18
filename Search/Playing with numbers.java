import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    BufferedReader rd;

    private Solution() throws IOException {
        rd = new BufferedReader(new InputStreamReader(System.in));
        compute();
    }

    private void compute() throws IOException {
        rd.readLine();
        int[] a = intarr();
        int n = a.length;
        int[] c = new int[4001];
        long off = 2000;
        for(int i=0;i<n;i++) {
            c[(int)(a[i]+off)]++;
        }
        long[] offSum = new long[4001];
        for(int p=-2000;p<=2000;p++) {
            long s = 0;
            for(int j=0;j<4001;j++) {
                s += Math.abs(p+j-off)*c[j];
            }
            offSum[p+2000] = s;
        }
        rd.readLine();
        int[] qs = intarr();
        StringBuilder buf = new StringBuilder();
        for(int j=0;j<qs.length;j++) {
            off += qs[j];
            long res;
            if(off < 0) {
                res = Math.abs(off)*n + offSum[0];
            } else if(off > 4000) {
                res = Math.abs(off-4000)*n + offSum[4000];
            } else {
                res = offSum[(int)off];
            }
            if(j > 0) {
                buf.append('\n');
            }
            buf.append(res);
        }
        out(buf);
    }

    private int[] intarr() throws IOException {
        return intarr(rd.readLine());
    }

    private int[] intarr(String s) {
        String[] q = s.split("\\s+");
        int n = q.length;
        int[] a = new int[n];
        for(int i=0;i<n;i++) {
            a[i] = Integer.parseInt(q[i]);
        }
        return a;
    }

    private static void out(Object x) {
        System.out.println(x);
    }

    public static void main(String[] args) throws IOException {
        new Solution();
    }
}
