import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        String[] strs = in.nextLine().split("\\s+");
        int size = Integer.parseInt(strs[0]);
        int k = Integer.parseInt(strs[1]);
        int[] input = new int[size];
        int[] index = new int[size + 1];
        for (int i = 0; i < size; i++) {
            input[i] = in.nextInt();
            index[input[i]] = i;
        }
        for (int i = 0; i < size; i++) {
            if (input[i] != size - i && k > 0) {
                k--;
                int correctIndex = index[size - i];
                input[correctIndex] = input[i];
                input[i] = size - i;
                index[input[i]] = i;
                index[input[correctIndex]] = correctIndex;
            }
        }
        for (int i = 0; i < size; i++) {
            System.out.print(input[i] + " ");
        }
        System.out.println("");
    }
}