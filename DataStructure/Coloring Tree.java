

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

class ColorVertex{
	List<Integer> edgeList;
	int color;
	HashSet<Integer> colorSet;
	int colorCount = 1;
	boolean visited;
	boolean towardsRoot;
	
	public void addEdge(int ColorVertex){
		if(edgeList == null) edgeList = new ArrayList<Integer>();
		edgeList.add(ColorVertex);
	}
	
	public int getColorSize(){
		return colorCount;
	}
	
	public void addColor(int color){
		this.color = color;
	}
	
	public HashSet<Integer> getColorSet(){
		if(colorSet == null){
			colorSet = new HashSet<Integer>();
			colorSet.add(color);
		}
		return colorSet;	
	}
	
	public void addColorSet(HashSet<Integer> childSet){	
		if(colorSet==null){
			colorSet = childSet;
			colorSet.add(color);
		}
		else if(colorSet.size() < childSet.size()){
			childSet.addAll(colorSet);
			colorSet = childSet;
		}
		else
			colorSet.addAll(childSet);
//		getColorSet().addAll(colorset);
	}
	

	public void addColorSet(int color){
		getColorSet().add(color);
	}
}

public class Solution {    
    int vertices;
    ColorVertex[] colorVertexList;    
    int root;    
    public Solution(int size, int root){
        vertices = size;
        colorVertexList = new ColorVertex[size];
        this.root = root;
     }

    public void addEdge(int node1, int node2){
    	    node1--;
    	    node2--;
    	    if(colorVertexList[node1] == null) colorVertexList[node1] = new ColorVertex();
    	    if(colorVertexList[node2] == null) colorVertexList[node2] = new ColorVertex();
    	    
        	colorVertexList[node1].addEdge(node2);
        	colorVertexList[node2].addEdge(node1);
    }
    
    public void addColor(String color, int node){
    	//if(colorVertexList[node] == null) colorVertexList[node] = new ColorVertex();
        colorVertexList[node].addColor(Integer.parseInt(color));
    }
    
    public int getColorsAtNode(int node){
        return colorVertexList[node-1].colorCount;
    }

    public void calculateColors(){
    	Stack<Integer> stack = new Stack<Integer>();
    	int currNode = root;
    	stack.push(currNode);
    	colorVertexList[root].towardsRoot=true;
    	
    	while(!stack.isEmpty()){
    		currNode = stack.peek();
    		ColorVertex colorVertex = colorVertexList[currNode];	
    		List<Integer> list = colorVertex.edgeList;
            
    		if(colorVertex.visited){
    			stack.pop();
	    		for(int node: list){
	    			//System.out.println("CurrNode:"+(currNode+1)+", list:"+colorVertexList[node]);
	    			if(!colorVertexList[node].towardsRoot){
		    			if(colorVertexList[node].colorSet == null)
		    				colorVertex.addColorSet(colorVertexList[node].color);
		    			else{
		    				colorVertex.addColorSet(colorVertexList[node].colorSet);
		    				colorVertexList[node].colorSet = null;
		    			}
	    			}
	    		}
	    		colorVertex.colorCount=colorVertex.getColorSet().size();
	    		colorVertex.towardsRoot=false;
    		}
    		else{
    			  colorVertex.visited = true;
                  for(Integer node:list){
                	  if(!colorVertexList[node].towardsRoot){
                		  stack.push(node);
                		  colorVertexList[node].towardsRoot=true;
                	  }
                  }
    		}
    	}
    	
    }
    
    public static void main(String[] args) {
    	long startTime = 0; 
    	try{
	        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
	        Scanner in = new Scanner(System.in);
	        String line = in.nextLine();
	        startTime = System.currentTimeMillis();
	        String[] lines = line.split(" ");
	        int N = Integer.parseInt(lines[0]);
	        int M = Integer.parseInt(lines[1]);
	        int root = Integer.parseInt(lines[2]) - 1;
	        Solution solution = new Solution(N, root);
	        for(int i=1; i<N; i++){
	            line = in.nextLine();
	            lines = line.split(" ");
	            solution.addEdge(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]));
	        }
	        
	        for(int i=0; i<N; i++){
	            line = in.nextLine();
	            solution.addColor(line,i);
	        }
	        solution.calculateColors();
	        for(int i=0; i<M;i++){
	            line = in.nextLine();            
	            System.out.println(solution.getColorsAtNode(Integer.parseInt(line)));
	        }
	        in.close();}
        catch(Exception ex){
        	ex.printStackTrace();
        }
        finally{
        	//System.out.println("Time:"+(System.currentTimeMillis()-startTime));
        }
    }
}