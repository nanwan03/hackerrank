import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static int primeSieve(int N){
        boolean[] isPrime = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= N using Sieve of Eratosthenes
        for (int i = 2; i*i <= N; i++) {

            // if i is prime, then mark multiples of i as nonprime
            // suffices to consider mutiples i, i+1, ..., N/i
            if (isPrime[i]) {
                for (int j = i; i*j <= N; j++) {
                    isPrime[i*j] = false;
                }
            }
        }

        // count primes
        int primes = 0;
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) primes++;
        }
        return primes;
    }
    static int brick(int n){
        int[] p = new int[n+1];
        p[1] = 1;
        if(n>=2)
        p[2] = 1;
        if(n>=3)
        p[3] = 1;
        if(n>=4)
        p[4] = 2;
        if(n>=5){
        for(int i = 5; i<=n; i++){
            p[i] = p[i-1]+p[i-4];
        }
    }
        return p[n];
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        for(int i = 0; i<T; i++){
            int n = s.nextInt();
            System.out.println(primeSieve(brick(n)));
        }
    }
}