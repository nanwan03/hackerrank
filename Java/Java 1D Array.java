import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public boolean rec(int[] arr, int n, int m, int i){
        if (i>=n) return true;
        if (arr[i]==1) return false;
        if (rec(arr,n,m,i+1)) return true;
        if (rec(arr,n,m,i+m)) return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for (int t=0;t<T;++t){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int arr[] = new int[n];
            
            for (int i=0;i<n;++i) 
                arr[i] = sc.nextInt();
            Solution sol = new Solution();
            boolean possibleToWin = sol.rec(arr,n,m,0);
            if (possibleToWin) System.out.println("YES");
            else System.out.println("NO");
        }
        
    }
}