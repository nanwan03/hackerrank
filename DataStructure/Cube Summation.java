import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static class myPoint implements Comparable<myPoint>{
        
        int x;
        int y;
        int z;
        
        public myPoint(int tx, int ty, int tz){
            x = tx;
            y = ty;
            z = tz;
        }
        
        public boolean greater(myPoint c){
            if(x >= c.x && y >= c.y && z >= c.z)
                return true;
            else
                return false;
        }
        
        public boolean lesser(myPoint c){
            if(x <= c.x && y <= c.y && z <= c.z)
                return true;
            else
                return false;
        }
        
        public int compareTo(myPoint c){
            if(x > c.x){
                return 1;
            }else if(x < c.x){
                return -1;
            }else if(y > c.y){
                return 1;
            }else if(y < c.y){
                return -1;
            }else if(z > c.z){
                return 1;
            }else if(z < c.z){
                return -1;
            }else{
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        for(int j = 0; j < num; j++){
            TreeMap<myPoint,Integer> cube = new TreeMap<myPoint,Integer>();
             int n = in.nextInt();
            int m = in.nextInt();
            in.nextLine();
            for(int i = 0; i < m; i++){
                String[] line = in.nextLine().split(" ");
                if(line[0].equals("UPDATE")){
                    cube.put(new myPoint(Integer.parseInt(line[1]),Integer.parseInt(line[2]),Integer.parseInt(line[3])),Integer.parseInt(line[4]));
                }else{
                    long sum = 0;
                    myPoint min = new myPoint(Integer.parseInt(line[1]),Integer.parseInt(line[2]),Integer.parseInt(line[3]));
                    myPoint max = new myPoint(Integer.parseInt(line[4]),Integer.parseInt(line[5]),Integer.parseInt(line[6]));
                    for(myPoint p : cube.keySet()){
                        if(p.greater(min) && p.lesser(max)){
                            sum += cube.get(p);
                        }
                    }
                    System.out.println(sum);
                }
            }
        }
    }
}