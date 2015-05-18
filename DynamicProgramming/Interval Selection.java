import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.InputStreamReader;

public class Solution {
  public static void main(String[] args) throws Exception{
    Scanner in = new Scanner(new InputStreamReader(System.in));
    int T = in.nextInt();
    while (T-->0) {
      int N = in.nextInt();
      Interval[] intervals = new Interval[N];
      for (int i=0; i<N; i++)
        intervals[i] = new Interval(in.nextInt(),in.nextInt());
      Arrays.sort(intervals);
      System.out.println(solve(intervals));
    }
  }

  public static int solve(Interval[] intervals) {
    ArrayList<Interval> picked = new ArrayList();
    picked.add(intervals[0]);
    picked.add(intervals[1]);
    for (int c=2; c<intervals.length; c++) {
      boolean triangle = false;
      Interval curr = intervals[c];
      ArrayList<Interval> intersectors = new ArrayList();
      for (int last = picked.size()-1; last>=0 && curr.start<=picked.get(last).end; last--)
        intersectors.add(picked.get(last));
      for (int i=0; i<intersectors.size(); i++) {
        for (int j=i+1; j<intersectors.size(); j++) {
          Interval int1 = intersectors.get(i);
          Interval int2 = intersectors.get(j);
          int start = Math.max(int1.start,Math.max(int2.start,curr.start));
          int end   = Math.min(int1.end  ,Math.min(int2.end  ,curr.end  ));
          if (start<=end) {triangle = true; break;}
        }
        if (triangle) break;
      }
      if (!triangle) picked.add(curr);
    }
    return picked.size();
  }
}

class Interval implements Comparable<Interval>{
  public int start, end;
  public Interval(int start, int end) {
    this.start = start; this.end = end;}
  public int compareTo(Interval i) {
    return this.end-i.end;}
  public String toString() {
    return "["+Integer.toString(this.start)+" "+Integer.toString(this.end)+"]";}
}