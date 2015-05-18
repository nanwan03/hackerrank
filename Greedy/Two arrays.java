import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int testcase = Integer.parseInt(in.nextLine());
        for (int i = 0; i < testcase; i++) {
            String[] strs = in.nextLine().split("\\s+");
            int size = Integer.parseInt(strs[0]);
            int target = Integer.parseInt(strs[1]);
            int[] arr1 = new int[size];
            int[] arr2 = new int[size];
            for (int j = 0; j < size; j++) {
                arr1[j] = in.nextInt();
            }
            in.nextLine();
            for (int j = 0; j < size; j++) {
                arr2[j] = in.nextInt();
            }
            if (i < testcase - 1) {
                in.nextLine();
            }
            Arrays.sort(arr1);
            Arrays.sort(arr2);
            solve(arr1, arr2, target);
        }
    }
    private static void solve(int[] arr1, int[] arr2, int k) {
        int left = 0;
        int right = arr1.length - 1;
        while (left <= right) {
            if (arr1[left] + arr2[right] < k || arr1[right] + arr2[left] < k) {
                System.out.println("NO");
                return;
            } else {
                left++;
                right--;
            }
        }
        System.out.println("YES");
    }
}