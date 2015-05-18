import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static class Pair implements Comparable<Pair>{
        final int player;
        final int bike;
        final long dist;
        public Pair(int p, int b, long d){
            player = p;
            bike = b;
            dist = d;
        }
        @Override public int compareTo(Pair o){
            if(dist != o.dist)
                return dist < o.dist ? -1 : 1;
            else if(player != o.player)
                return player < o.player ? -1 : 1;
            else if(bike != o.bike)
                return bike < o.bike ? -1 : 1;
            else
                return 0;
        }
    }
    
    public static class MaxFlow{
        final List<Set<Integer>> adj = new ArrayList<>();
        final int source, destination;
        public MaxFlow(int node, int src, int dst){
            source = src;
            destination = dst;
            for(int i = 0; i < node; ++i)
                adj.add(new HashSet<Integer>());
        }
        public void add(int u, int v){
            adj.get(u).add(v);
        }
        public boolean augment(){
            int[] pred = new int[adj.size()];
            Arrays.fill(pred, -1);
            pred[source] = source;
            Set<Integer> prev = Collections.singleton(source);
            while(prev.size() > 0 && !prev.contains(destination)){
                Set<Integer> next = new HashSet<>();
                for(int i: prev)
                    for(int j: adj.get(i))
                        if(pred[j] < 0){
                            pred[j] = i;
                            next.add(j);
                        }
                prev = next;
            }
            if(prev.size() == 0)
                return false;
            for(int i = destination; i != source; i = pred[i]){
                adj.get(pred[i]).remove(i);
                adj.get(i).add(pred[i]);
            }
            return true;
        }
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int player = scan.nextInt();
        int bike = scan.nextInt();
        int target = scan.nextInt();
        long[] playerX = new long[player];
        long[] playerY = new long[player];
        for(int i = 0; i < player; ++i){
            playerX[i] = scan.nextLong();
            playerY[i] = scan.nextLong();
        }
        Set<Pair> pairs = new TreeSet<>();
        for(int i = 0; i < bike; ++i){
            long x = scan.nextLong();
            long y = scan.nextLong();
            for(int j = 0; j < player; ++j)
                pairs.add(new Pair(j, i, (playerX[j] - x) * (playerX[j] - x) + (playerY[j] - y) * (playerY[j] - y)));
        }
        MaxFlow flow = new MaxFlow(2 + player + bike, 0, 1);
        for(int i = 0; i < player; ++i)
            flow.add(0, i + 2);
        for(int i = 0; i < bike; ++i)
            flow.add(player + 2 + i, 1);
        int match = 0;
        for(Pair p: pairs){
            flow.add(p.player + 2, p.bike + player + 2);
            if(flow.augment()){
                if(++match == target){
                    System.out.println(p.dist);
                    break;
                }
            }
        }
        if(match < target)
            System.out.println("ERROR");
    }
}