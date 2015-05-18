import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine());

        for (int i = 0; i < cases; i++) {
            char[] A = in.readLine().toCharArray();
            char[] B = in.readLine().toCharArray();

            Stack<Character> AS = stack(A);
            Stack<Character> BS = stack(B);

            int index = 0;
            char[] output = new char[A.length + B.length];
            char a1 = '\0';
            char b1 = '\0';
            while (true) {
                if (AS.empty() && BS.empty()) {
                    break;
                }

                if (BS.empty()) {
                    output[index++] = AS.pop().charValue();
                    continue;
                }

                if (AS.empty()) {
                    output[index++] = BS.pop().charValue();
                    continue;
                }

                if (AS.peek().charValue() < BS.peek().charValue()) {
                    output[index++] = AS.pop().charValue();
                } else if (BS.peek().charValue() < AS.peek().charValue()) {
                    output[index++] = BS.pop().charValue();
                } else {
                    List<Character> tempA = new ArrayList<Character>();
                    List<Character> tempB = new ArrayList<Character>();

                    a1 = AS.pop().charValue();
                    b1 = BS.pop().charValue();

                    tempA.add(a1);
                    tempB.add(b1);

                    while (true) {
                        if (a1 > 0 && b1 == '\0') {
                            if (tempB.get(tempB.size() - 1) < a1 ) {
                                for (char c : tempB) {
                                    output[index++] = c;
                                }

                                for (int s1 = tempA.size() - 1; s1 >= 0; s1--) {
                                    AS.push(tempA.get(s1));
                                }
                            } else {
                                for (char c : tempA) {
                                    output[index++] = c;
                                }

                                for (int s1 = tempB.size() - 1; s1 >= 0; s1--) {
                                    BS.push(tempB.get(s1));
                                }
                            }

                            break;
                        } else if (b1 > 0 && a1 == '\0') {
                            if (tempA.get(tempA.size() - 1) < a1 ) {
                                for (char c : tempA) {
                                    output[index++] = c;
                                }

                                for (int s1 = tempB.size() - 1; s1 >= 0; s1--) {
                                    BS.push(tempB.get(s1));
                                }
                            } else {
                                for (char c : tempB) {
                                    output[index++] = c;
                                }

                                for (int s1 = tempA.size() - 1; s1 >= 0; s1--) {
                                    AS.push(tempA.get(s1));
                                }
                            }

                            break;
                        }

                        if (a1 < b1) {
                            for (char c : tempA) {
                                output[index++] = c;
                            }

                            for (int s1 = tempB.size() - 1; s1 >= 0; s1--) {
                                BS.push(tempB.get(s1));
                            }

                            break;
                        }

                        if (a1 > b1) {
                            for (char c : tempB) {
                                output[index++] = c;
                            }

                            for (int s1 = tempA.size() - 1; s1 >= 0; s1--) {
                                AS.push(tempA.get(s1));
                            }

                            break;
                        }

                        if (a1 == '\0' && b1 == '\0') {
                            for (int s1 = tempA.size() - 1; s1 >= 0; s1--) {
                                AS.push(tempA.get(s1));
                            }

                            for (int s1 = tempB.size() - 1; s1 >= 0; s1--) {
                                BS.push(tempB.get(s1));
                            }

                            output[index++] = AS.pop().charValue();
                            break;
                        }

                        if (!AS.isEmpty()) {
                            a1 = AS.pop().charValue();
                            tempA.add(a1);
                        } else {
                            a1 = '\0';
                        }

                        if (!BS.isEmpty()) {
                            b1 = BS.pop().charValue();
                            tempB.add(b1);
                        } else {
                            b1 = '\0';
                        }
                    }
                }
            }

            String result = new String(output);
            System.out.println(result);

        }
    }

    public static Stack<Character> stack(char[] A) {
        Stack<Character> AS = new Stack<Character>();
        for (int a = A.length - 1; a >= 0; a--) {
            AS.push(A[a]);
        }

        return AS;
    }


}