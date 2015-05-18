import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	private static int fordFulkerson(int[][] adjacency, int src, int tgt)
	{
		int[][] fnet = new int[adjacency.length][adjacency.length];
		int[] q = new int[adjacency.length];
		int[] temp = new int[adjacency.length];
		int qf,qb;

		int flow = 0;
		while(true)
		{
			// find the augmenting path
			Arrays.fill(temp, -1);
			qf = qb = 0;
			temp[q[qb++] = src] = -2;
			// perform BFS on the graph for the given flow
			while(qb > qf && temp[tgt] == -1)
			{
				for(int u = q[qf++], v = 0; v < adjacency.length; v++)
				{
					if(temp[v] == -1 && fnet[u][v] - fnet[v][u] < adjacency[u][v])
					{
						temp[v] = u;
						q[qb++] = v;
					}
				}
			}
			if(temp[tgt] == -1)
				break;
			int bot = Integer.MAX_VALUE;
			// parsing the path from target to source
			// to find the minimum flow possible across the path
			for(int v = tgt, u = temp[v]; u >= 0; v = u, u = temp[v])
			{
				bot =  Math.min(bot, adjacency[u][v] - fnet[u][v] + fnet[v][u]);
			}
			// parsing the path from target to source
			// to add mininum flow possible
			for(int v = tgt, u = temp[v]; u >= 0; v = u, u = temp[v] )
			{
				fnet[u][v] += bot;
			}
			flow += bot;
		}
		return flow;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int tests = Integer.parseInt(reader.readLine());
		for (int t = 0; t < tests; t++)
		{
			String[] tuple = reader.readLine().split(" ");
			int nodes_count = Integer.parseInt(tuple[0]);
			int crab_legs_count = Integer.parseInt(tuple[1]);
			int edges_count = Integer.parseInt(tuple[2]);
			int[][] adjacency = new int[nodes_count*2+2][nodes_count*2+2];
			int[] degree = new int[nodes_count+1];
			for (int i = 0; i < edges_count; i++) {
				tuple = reader.readLine().split(" ");
				int a = Integer.parseInt(tuple[0]);
				int b = Integer.parseInt(tuple[1]);
				++degree[a];
				++degree[b];
				adjacency[2*a][2*b+1] = 1;
				adjacency[2*b][2*a+1] = 1;
			}
			// form a bridge from source to even
			// form a bridge from odd to target
			for(int i = 1; i <= nodes_count; ++i)
			{
				adjacency[0][2*i] = Math.min(crab_legs_count, degree[i]);
				// odd to target flow = 1
				adjacency[2*i+1][1] = 1;
			}
			int result = fordFulkerson(adjacency, 0, 1);
			System.out.println(result);
		}
	}

}
