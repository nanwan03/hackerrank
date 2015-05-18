import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Solution1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int testNum = scanner.nextInt();
		scanner.nextLine();
		for (int t = 0; t < testNum; t++) {
			String key = scanner.nextLine();
			
			String ciphertext = scanner.nextLine();
			Map<Character, Integer> map = new HashMap<Character, Integer>();
			Map<Character, Character> substitution = new HashMap<Character, Character>();
			int k = 0;
			for (int i = 0; i < key.length(); i++) {
				if (!map.containsKey(key.charAt(i))) {
					//System.out.println("ch : " + key.charAt(i) + "index :" + k);
					map.put(key.charAt(i), k++);
					
				}			
			}
			int n = map.size();
			int[] loc = new int[n];
			int row = (26 % n == 0 ? 0 : 1) + 26 / n;
			char[][] ch = new char[row][n];
			Set<Character> set = new HashSet<Character>();
			k = 0;
			for (int i = 0; i < key.length(); i++) {
				if (!set.contains(key.charAt(i))) {
					set.add(key.charAt(i));
					ch[0][k++] = key.charAt(i);
				}
				
			}
			char alp = 'A';
			k = 0;
			for (int i = 1; i < row && alp <= 'Z'; i++) {
				for (int j = 0; j < n && alp <= 'Z'; ) {
					if (map.containsKey(alp)) {
						loc[k++] = map.get(alp);
					} else {
						ch[i][j] = alp;
						j++;
					}
					alp++;
				}
			}
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < row; j++) {
					if (ch[j][loc[i]] >= 'A' && ch[j][loc[i]] <= 'Z') {
						builder.append(ch[j][loc[i]]);
					}
				}
			}
			String str = builder.toString();
			for (char c = 'A'; c <= 'Z'; c++) {
				substitution.put(str.charAt(c - 'A'), c);
			}
			for (int i = 0; i < ciphertext.length(); i++) {
				if (ciphertext.charAt(i) == ' ') {
					System.out.print(' ');
				} else {
					System.out.print(substitution.get(ciphertext.charAt(i)));
				}
			}
			System.out.println();
		}
	}

}