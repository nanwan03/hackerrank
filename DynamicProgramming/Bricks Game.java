import java.util.Scanner;

public class Solution {
	private static long[] array;
	private static long[] sum;
	private static long[] value;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		long[] result = new long[T];
		for (int i = 0; i < T; i++) {
			int N = in.nextInt();
			array = new long[N];
			sum = new long[N];
			value = new long[N];
			for (int j = N - 1; j >= 0; j--) {
				array[j] = in.nextInt();
			}
			sum[0] = array[0];
			for (int j = 1; j < N; j++) {
				sum[j] = sum[j - 1] + array[j];
			}
			value[0] = sum[0];
			value[1] = sum[1];
			value[2] = sum[2];
			for (int j = 3; j < N; j++) {
				long temp = (sum[j] - sum[j - 1]) + (sum[j - 1] - value[j - 1]);
				value[j] = value[j] > temp ? value[j] : temp;
				temp = (sum[j] - sum[j - 2]) + (sum[j - 2] - value[j - 2]);
				value[j] = value[j] > temp ? value[j] : temp;
				temp = (sum[j] - sum[j - 3]) + (sum[j - 3] - value[j - 3]);
				value[j] = value[j] > temp ? value[j] : temp;
			}
			result[i] = value[N - 1];
		}
		in.close();
		for (int i = 0; i < T; i++) {
			System.out.println(result[i]);
		}
	}
}
