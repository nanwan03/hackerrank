import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    
    static int cityNum;
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in=new Scanner(System.in);
        cityNum=in.nextInt();
        int journeyNum=in.nextInt();
        int[][] edge=new int[cityNum-1][2];

        ArrayList<ArrayList<Integer>> cityConnect=new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<cityNum;i++){
            ArrayList<Integer> city=new ArrayList<Integer>();
            cityConnect.add(city);
        }
        
        for(int i=0;i<cityNum-1;i++){
            edge[i][0]=in.nextInt();
            edge[i][1]=in.nextInt();
            cityConnect.get(edge[i][0]-1).add(edge[i][1]-1);
            cityConnect.get(edge[i][1]-1).add(edge[i][0]-1);
        }
        int[] dist0=findDist(cityConnect,0);
        int position1=max_Index(dist0);
        int[] dist1=findDist(cityConnect,position1);
        
        int position2=max_Index(dist1);
        int[] dist2=findDist(cityConnect,position2);
        
        long longestLength=dist1[position2];
        
        for(int i=0;i<journeyNum;i++){
            int currentCity=in.nextInt();
            long journeyLength=in.nextLong();
            long totalDis=Math.max(dist1[currentCity-1],dist2[currentCity-1])+longestLength*(journeyLength-1);
            System.out.println(totalDis);
        }
        in.close();
    }
    
    private static int[] findDist(ArrayList<ArrayList<Integer>> cityConnect,int position){
        PriorityQueue<Integer> a=new PriorityQueue<Integer>();
        a.add(position);
        int[] dist=new int[cityNum];
        Arrays.fill(dist,-1);
        dist[position]=0;
        while(a.size()!=0){
            int temp=a.poll();
            for(int i=0;i<cityConnect.get(temp).size();i++){
                int currentCity=cityConnect.get(temp).get(i);
                if(dist[currentCity]==-1){
                    dist[currentCity]=dist[temp]+1;
                    a.add(currentCity);
                }
            }
        }
        return dist;
    }
    
    private static int max_Index(int[] array){
        int max=-1;
        int count=0;
        for(int i=0;i<array.length;i++){
            if(array[i]>max){
                max=array[i];
                count=i;
            }
        }
        return count;
    }
}