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
    		String str = new String(in.nextLine());
    		String[] strs = str.split(" ");
    		int additional = Integer.parseInt(strs[0]);
    		String str1 = strs[1];
    		String str2 = strs[2];
    		System.out.println(Math.max(solution(additional, str1, str2), solution(additional, str2, str1)));
    	}
    }
    private static int solution(int additional, String str1, String str2) {
    	int rst = 0;
    	List<Integer> list = new ArrayList<Integer>();
    	for (int i = 0; i < str1.length(); i++) {
    		list.clear();
    		for (int j = 0; j < Math.min(str2.length(), str1.length() - i); j++) {
    			if (str1.charAt(i + j) != str2.charAt(j)) {
    				list.add(1);
    			} else {
    				list.add(0);
    			}
    		}
    		int start = 0;
    		int count = 0;
    		for (int j = 0; j < list.size(); j++) {
    			count += list.get(j);
    			for (; count > additional; start++) {
    				count -= list.get(start);
    			}
    			rst = Math.max(rst, j - start + 1);
    		}
    	}
    	return rst;
    }
}