import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Solution {
    static Random random = new Random();

    public static void main(String[] args) throws IOException {
        InputReader reader = new InputReader(System.in);
        int N = reader.readInt();
        int Q = reader.readInt();
        int[] A = new int[N];
        for (int n=0; n<N; n++) {
            A[n] = reader.readInt();
        }
        Treap T1 = new Treap(random.nextDouble(), A[0], null, null);
        Treap T2 = new Treap(random.nextDouble(), A[1], null, null);
        for (int n=2; n<N; n++) {
            if (n%2 == 0) {
                T1 = T1.add(A[n]);
            } else {
                T2 = T2.add(A[n]);
            }
        }
        StringBuilder output = new StringBuilder();
        for (int q=0; q<Q; q++) {
            int type = reader.readInt();
            int L = reader.readInt()-1;
            int R = reader.readInt();
            Treap[] T1x = T1.split((R+1)/2);
            Treap[] T1y = (T1x[0] == null) ? new Treap[] {null, null} : T1x[0].split((L+1)/2);
            Treap[] T2x = T2.split(R/2);
            Treap[] T2y = (T2x[0] == null) ? new Treap[] {null, null} : T2x[0].split(L/2);
            if (type == 1) {
                T1 = Treap.merge(T1y[0], Treap.merge(T2y[1], T1x[1]));
                T2 = Treap.merge(T2y[0], Treap.merge(T1y[1], T2x[1]));
            } else {
                long sum = ((T1y[1] == null) ? 0 : T1y[1].sum) + ((T2y[1] == null) ? 0 : T2y[1].sum);
                output.append(sum).append('\n');
            }
        }
        System.out.print(output);
    }

    static final class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        private int read() throws IOException {
            if (curChar >= numChars) {
                curChar = 0;
                numChars = stream.read(buf);
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public final int readInt() throws IOException {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int res = 0;
            do {
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }

    static class Treap {
        double priority;
        int value;
        Treap left;
        Treap right;
        int size;
        long sum;
        
        Treap(double priority, int value, Treap left, Treap right) {
            this.priority = priority;
            this.value = value;
            this.left = left;
            this.right = right;
            size = 1;
            sum = value;
            if (left != null) {
                size += left.size;
                sum += left.sum;
            }
            if (right != null) {
                size += right.size;
                sum += right.sum;
            }            
        }

        Treap[] split(int index) {
            Treap L = null;
            Treap R = null;
            Treap temp = null;
            int curindex = 1 + ((left == null) ? 0 : left.size);

            if (curindex <= index) {
                if (right != null) {
                    Treap[] subSplit = right.split(index-curindex);
                    temp = subSplit[0];
                    R = subSplit[1];
                }
                L = new Treap (priority, value, left, temp);
            } else {
                if (left != null) {
                    Treap[] subSplit = left.split(index);
                    L = subSplit[0];
                    temp = subSplit[1];
                }
                R = new Treap(priority, value, temp, right);
            }
            return new Treap[] {L, R};
        }

        static Treap merge(Treap L, Treap R) {
            if (L == null) return R;
            if (R == null) return L;

            if (L.priority > R.priority) {
                Treap subMerge = merge(L.right, R);
                return new Treap(L.priority, L.value, L.left, subMerge);
            } else {
                Treap subMerge = merge(L, R.left);
                return new Treap(R.priority, R.value, subMerge, R.right);
            }
        }

        Treap insert(int pos, int value) {
            Treap[] split = split(pos) ; 
            Treap singleton = new Treap(random.nextDouble(), value, null, null);
            return merge(merge(split[0], singleton), split[1]) ;
        }
        
        Treap add(int value) {
            Treap singleton = new Treap(random.nextDouble(), value, null, null);
            return merge(this, singleton) ;            
        }

        @Override
        public String toString() {
            String s = "";
            if (left != null) {
                s = left.toString();
            }
            s += value + " ";
            if (right != null) {
                s += right.toString();
            }
            return s;
        }

    }
    
}
