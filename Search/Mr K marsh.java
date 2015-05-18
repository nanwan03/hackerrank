import java.util.Arrays;
import java.util.Scanner;


public class Solution {

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		
		int row = cin.nextInt();
		int col = cin.nextInt();
		char a[][] = new char[row][];
		for (int i=0; i<row; i++) 
			a[i] = cin.next().toCharArray();
		int sum[][] = new int [row][col];
		for (int i=0; i<row; i++) {
			sum[i][0] = a[i][0] == '.' ? 1 : 0;
			for (int j=1; j<col; j++)
				sum[i][j] = sum[i][j - 1] + (a[i][j] == '.' ? 1 : 0);
		}
		
		int ans = 0;
		for (int col1=0; col1<col; col1++) {
			for (int col2=col1+1; col2<col; col2++) {
				boolean isok[] = new boolean[row];
				for (int i=0; i<row; i++)
					isok[i] = (sum[i][col2] - (col1 == 0 ? 0 : sum[i][col1 - 1])) == col2 - col1 + 1;
				
				int minPos = -1;
				for (int i=0; i<row; i++) {
					if (isok[i]) {
						if (minPos == -1) minPos = i;
						if (i > minPos) ans = Math.max(ans, 2 * (col2 - col1 + i - minPos));
					} 
					
					if (!(a[i][col1] == '.' && a[i][col2] == '.')) {
						minPos = -1;
					}
				}
					
			}
		}
		
		if (ans == 0) System.out.println("impossible");
		else System.out.println(ans);
		
		cin.close();
	}

}
