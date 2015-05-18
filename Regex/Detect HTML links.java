import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

		String regexStart = "<\\s*a";
		String regexEnd = "</\\s*a";
		String regexLink = "href=\"[^<> ]*\"\\W";
		String regexContent = ">[^<>]+<";

		Pattern ptAStart= Pattern.compile(regexStart);
		Pattern ptAEnd= Pattern.compile(regexEnd);
		Pattern ptLink = Pattern.compile(regexLink);
		Pattern ptContent = Pattern.compile(regexContent);
        
        Scanner in = new Scanner(System.in);
        int numLines = Integer.parseInt( in.nextLine());
        for(int i=0;i<numLines; i++){
            String line = in.nextLine();
            
            Matcher mAStart = ptAStart.matcher(line);
            Matcher mAEnd = ptAEnd.matcher(line);
            while(mAStart.find() && mAEnd.find()){
            	
            	Matcher mLink = ptLink.matcher(line.substring(mAStart.end(), mAEnd.start()));
            	if(mLink.find()){
            		String wholeLink = mLink.group();
            		wholeLink = wholeLink.substring(wholeLink.indexOf('"')+1, wholeLink.length()-2);
            		String sub = line.substring(mAStart.end());
//            		System.out.println(sub);
            		Matcher mContent = ptContent.matcher(sub);
            		String content = "";
            		if(mContent.find()){
            			content = mContent.group();
            			content = content.substring(1, content.length()-1);
            		}
            		System.out.println(wholeLink.trim() + "," + content.trim());
            	}
            	
            }
            
            
        }
        
    }
}