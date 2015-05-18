import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        Stack<Integer> stack = new Stack<Integer>();
        long res = 0;
        for (int i = 0; i < size; i++) {
            int cur = sc.nextInt();
            if (!stack.isEmpty() && stack.peek() < cur) {
                int tmp = stack.pop();
                long dupCount = 1;
                while (!stack.isEmpty() && stack.peek() < cur) {
                    int top = stack.pop();
                    if (top == tmp) {
                        dupCount++;
                    } else {
                        //update possible routes
                        res += dupCount * (dupCount - 1);
                        tmp = top;
                        dupCount = 1;
                    }
                }
                res += dupCount * (dupCount - 1);
            }
            stack.push(cur);
        }
        while (!stack.isEmpty()) {
            int tmp = stack.pop();
            long dupCount = 1;
            while (!stack.isEmpty() && stack.peek() == tmp) {
                dupCount++;
                stack.pop();
            } 
            res += dupCount * (dupCount - 1);
        }
        System.out.println(res);
    }
}