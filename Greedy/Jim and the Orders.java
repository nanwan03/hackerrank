import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Node {
    int index;
    int endTime;
    Node(int index, int endTime) {
        this.index = index;
        this.endTime = endTime;
    }
}
class NodeComparator implements Comparator<Node> {
    public int compare(Node a, Node b) {
        if (a.endTime == b.endTime) {
            return a.index - b.index;
        } else {
            return a.endTime - b.endTime;
        }
    }
}

public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int orders = Integer.parseInt(in.nextLine());
        List<Node> rst = new ArrayList<Node>();
        for (int i = 1; i <= orders; i++) {
            String[] inputs = in.nextLine().split("\\s+");
            int startTime = Integer.parseInt(inputs[0]);
            int duration = Integer.parseInt(inputs[1]);
            rst.add(new Node(i, startTime + duration));
        }
        Collections.sort(rst, new NodeComparator());
        for (Node node : rst) {
            System.out.print(node.index + " ");
        }
        System.out.println("");
    }
}