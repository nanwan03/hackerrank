import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class SegmentTree
{
    private int level;
    private int count;
    private SegmentTree left;
    private SegmentTree right;
    private int n;
    public SegmentTree(int level)
    {
        this.level = level;
        this.n = 1<<level;
        this.count = 0;
        if(level>0)
        {
            this.left = new SegmentTree(level-1);
            this.right = new SegmentTree(level-1);
        }
    }
    public void add(int k)
    {
        //System.out.printf("level %d add %d\n",level,k);
        this.count++;
        if(this.level>0)
        {
            if(k>=n/2)
            {
                this.right.add(k-n/2);
            }
            else
            {
                this.left.add(k);
            }
        }
    }
    public void remove(int k)
    {
        //System.out.printf("level %d remove %d\n",level,k);
        this.count--;
        if(this.level>0)
        {
            if(k>=n/2)
            {
                this.right.remove(k-n/2);
            }
            else
            {
                this.left.remove(k);
            }
        }
    }
    public int count(int start,int end)
    {
        //System.out.printf("count level %d from %d to %d\n",level,start,end);
        //System.out.printf("n = %d\n",n);
        if(start==0 && end==n)
        {
            return this.count;
        }
        if(start>=n/2)
        {
            return this.right.count(start-n/2,end-n/2);
        }
        if(end<=n/2)
        {
            return this.left.count(start,end);
        }
        return this.left.count(start,n/2)+this.right.count(0,end-n/2);
    }
}

public class Solution {
    private static SegmentTree st;
    private static long count;
    private static int n;
    private static int t;
    private static ArrayList<Integer>[] children;

    private static void search(int node)
    {
        //System.out.printf("Search %d\n",node);
        int start = Math.max(node-t,0);
        int end = Math.min(node+t+1,n);
        count += st.count(start,end);
        //System.out.printf("count = %d\n",count);
        st.add(node);
        for(int child:children[node])
        {
            search(child);
        }
        st.remove(node);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        t = sc.nextInt();
        children = (ArrayList<Integer>[]) new ArrayList[n];
        for(int i=0;i<n;i++)
        {
            children[i] = new ArrayList<Integer>();
        }
        boolean[] notRoot = new boolean[n];
        for(int i=0;i<n-1;i++)
        {
            int p = sc.nextInt()-1;
            int c = sc.nextInt()-1;
            children[p].add(c);
            notRoot[c] = true;
        }
        int root = -1;
        for(int i=0;i<n;i++)
        {
            if(!notRoot[i])
            {
                root = i;
            }
        }
        count = 0;
        int level = 0;
        while(1<<level<n)
        {
            level++;
        }
        //System.out.printf("level = %d\n",level);
        st = new SegmentTree(level);
        count = 0;
        search(root);
        System.out.println(count);
    }
}