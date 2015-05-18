import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.nextLine();

        Map<String,Set<String>> html = new TreeMap<String,Set<String>>();
		for (int i = 0; i < n; i++) {
			s = in.nextLine();
			Pattern p = Pattern.compile("(<([a-z0-9]+)(\"[^\"]*\"|'[^']*'|[^'\">])*>)");
            Pattern a = Pattern.compile(" ([a-z]+)=");
			Matcher m = p.matcher(s); 
            while (m.find()){
                Set<String> attributes = new TreeSet<String>();
                Matcher m2 = a.matcher(m.group(0));
                while (m2.find()) {
                    attributes.add(m2.group(1));
                }
				if (!html.containsKey(m.group(2))) {
					html.put(m.group(2), attributes);
				} else {
					attributes.addAll(html.get(m.group(2)));
					html.put(m.group(2), attributes);
				}
            }

        }
        for (Map.Entry<String,Set<String>> entry : html.entrySet()) {
            System.out.print(entry.getKey() + ":");
            Set<String> temp = entry.getValue();
            String[] attArray = temp.toArray(new String[0]);
            if (attArray.length > 0) {
                System.out.print(attArray[0]);
                for (int i = 1; i < attArray.length; i++) {
                    System.out.print("," + attArray[i]);
                }
            }
            System.out.println();
        }
   }
}