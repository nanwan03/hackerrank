import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while(in.hasNextLine()) {
            sb.append(in.nextLine());
        }

        Pattern pattern = Pattern.compile("< *([a-z0-9]+)[^>]*>");
        Matcher matcher = pattern.matcher(sb);
        SortedSet<String> tags = new TreeSet<String>();
        while (matcher.find()) {
            tags.add(matcher.group(1));
        }

        boolean first = true;
        for (String tag : tags) {
            if (!first) {
                System.out.print(";");
            } else {
                first = false;
            }
            System.out.print(tag);
        }
        System.out.println("");
    }
}