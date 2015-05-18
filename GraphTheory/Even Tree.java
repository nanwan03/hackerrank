/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
import java.util.*;

class Solution{
	public static void main( String args[] ){
// helpers for input/output		
		Scanner in = new Scanner(System.in);
		
		int N,M;
		N = in.nextInt();
                M = in.nextInt();
                HashSet<Integer>[] graph = new HashSet[N+1];
                int[] rank = new int[N+1];
            for(int i = 1; i < N+1; i++) {
                    graph[i] = new HashSet<Integer>();
                rank[i] = 0;
            }
			
		for(int i=0; i<M; i++){
		  int v1 = in.nextInt();
                  int v2 = in.nextInt();
                  graph[v1].add(v2);
                  graph[v2].add(v1);
		}
		
                int root = 1;
                HashSet<Integer> visited = new HashSet<Integer>();
                visit(root, rank, visited, graph);
                int total = 0;
                for (int i = 2; i < N; i++) {
                    if (rank[i] % 2 == 1) total++;
                }
                System.out.println(total);
	}
    
        public static void visit(int node, int[] rank, HashSet<Integer> visited, HashSet<Integer>[] graph)
        {
            visited.add(node);
            for (int child : graph[node]) {
                if (!visited.contains(child)) {
                    visit(child, rank, visited, graph);
                    rank[node] += rank[child]+1;
                }
            }
        }
}