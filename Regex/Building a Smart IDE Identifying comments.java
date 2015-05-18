import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        Pattern date_pattern =
                Pattern.compile("(/\\*{1,2}[^\\*]*?\\*{1,2}/|//[^\\n\\r]*)");
        String input;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        while ((input = reader.readLine()) != null) {
            builder.append(input).append("\n");
        }
        reader.close();

        Matcher matcher = date_pattern.matcher(builder.toString());

        while (matcher.find()) {
            String result = matcher.group(1);
            if (result.startsWith("/*")) {
                for (String line : result.split("[\n\r]")) {
                    System.out.println(line.trim());
                }
            } else {
                System.out.println(result.trim());
            }
        }
    }
}