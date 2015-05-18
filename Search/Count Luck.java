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
            int row = in.nextInt();
            int col = in.nextInt();
            in.nextLine();
            char[][] maze = new char[row][col];
            for (int j = 0; j < row; j++) {
                maze[j] = in.nextLine().toCharArray();
            }
            int target = Integer.parseInt(in.nextLine());
            System.out.println(solve(maze, target));
        }
    }
    private static String solve(char[][] maze, int target) {
        int row = maze.length;
        int col = maze[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (maze[i][j] == 'M') {
                    int rst = DFS(maze, i, j);
                    if (rst == target) {
                        return "Impressed";
                    } else {
                        return "Oops!";
                    }
                }
            }
        }
        return "Error!";
    }
    private static int DFS(char[][] maze, int i, int j) {
        int row = maze.length;
        int col = maze[0].length;
        int rst = 0;
        if (maze[i][j] == '*') {
            return 0;
        }
        maze[i][j] = 'X';
        int path = 0;
        if (i > 0 && (maze[i - 1][j] == '.' || maze[i - 1][j] == '*')) {
            path++;
        }
        if (i + 1 < row && (maze[i + 1][j] == '.' || maze[i + 1][j] == '*')) {
            path++;
        }
        if (j > 0 && (maze[i][j - 1] == '.' || maze[i][j - 1] == '*')) {
            path++;
        }
        if (j + 1 < col && (maze[i][j + 1] == '.' || maze[i][j + 1] == '*')) {
            path++;
        }
        if (path > 1) {
            rst = 1;
        }
        if (i + 1 < row && maze[i + 1][j] != 'X') {
            int temp = DFS(maze, i + 1, j);
            if (temp != -1) {
                return temp + rst;
            }
        }
        if (i > 0 && maze[i - 1][j] != 'X') {
            int temp = DFS(maze, i - 1, j);
            if (temp != -1) {
                return temp + rst;
            }
        }
        if (j + 1 < col && maze[i][j + 1] != 'X') {
            int temp = DFS(maze, i, j + 1);
            if (temp != -1) {
                return temp + rst;
            }
        }
        if (j > 0 && maze[i][j - 1] != 'X') {
            int temp = DFS(maze, i, j - 1);
            if (temp != -1) {
                return temp + rst;
            }
        }
        return -1;
    }
}