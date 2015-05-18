import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static final int MOD = 1000000007;
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int testcase = Integer.parseInt(in.nextLine());
        for (int i = 0; i < testcase; i++) {
            String[] str = in.nextLine().split("\\s+");
            int row = Integer.parseInt(str[0]);
            int col = Integer.parseInt(str[1]);
            long[] cutRow = new long[row - 1];
            long[] cutCol = new long[col - 1];
            for (int j = 0; j < row - 1; j++) {
                cutRow[j] = in.nextLong();
            }
            for (int j = 0; j < col - 1; j++) {
                cutCol[j] = in.nextLong();
            }
            if (i < testcase - 1) {
                in.nextLine();
            }
            Arrays.sort(cutRow);
            Arrays.sort(cutCol);
            solve(cutRow, cutCol);
        }
    }
    private static void solve(long[] cutRow, long[] cutCol) {
        long rowPiece = 1;
        long colPiece = 1;
        int index1 = cutRow.length - 1;
        int index2 = cutCol.length - 1;
        long rst = 0;
        while (index1 >= 0 && index2 >= 0) {
            if (cutRow[index1] >= cutCol[index2]) {
                rst = (rst + ((cutRow[index1--] * rowPiece) )) % MOD;
                colPiece++;
            } else {
                rst = (rst + ((cutCol[index2--] * colPiece) )) % MOD;
                rowPiece++;
            }
        }
        while (index1 >= 0) {
            rst = (rst + ((cutRow[index1--] * rowPiece) )) % MOD;
        }
        while (index2 >= 0) {
            rst = (rst + ((cutCol[index2--] * colPiece) )) % MOD;
        }
        System.out.println(rst);
    }
}