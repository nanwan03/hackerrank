import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    		private static class City implements Comparable<City> {
		public long population=0;
		public long distance=0;
		
		@Override
		public int compareTo(City o) {
			// TODO Auto-generated method stub
			if(this.population < o.population ){
				return -1;
			}else if(this.population > o.population){
				return 1;
			}
			return 0;
		}
		
		public boolean equalsTo(City o){
			if(this.population == o.population && this.distance==o.distance){
			return true;
			}
			return false;
		}
	}
	
	private static class Node{
		public City city;
		public Node leftNode = null;
		public Node rightNode = null;
		public long leftSum=0;
		public long rightSum=0;
		public long numLeft=0;
		public long numRight=0;
		
		public Node(City city){
			this.city = city;
		}
	}
	
	private static class Tree{
		public Node root=null;
		
		public void insert(Node node){
			this.root= insert(node,root);
		}
		
		private Node insert(Node node,Node root){
			if(root==null){
				return node;
			}
			if(node.city.distance <= root.city.distance ){
				root.leftSum+= node.city.distance;
				root.numLeft+=1;
				root.leftNode = insert(node,root.leftNode);
			}else{
				root.rightSum+=node.city.distance;
				root.numRight+=1;
				root.rightNode=insert(node,root.rightNode);
			}
			return root;
		}
		
		public long search(City c){
			return search(c,root);
		}
		
		private long search(City c,Node root){
			long sumOfDistances = 0;
			if(root.city.equalsTo(c)){
				//System.out.println("Found");
				sumOfDistances += (root.numLeft*root.city.distance - root.leftSum) + (root.rightSum - root.numRight*root.city.distance);
			}else if(c.distance <= root.city.distance ){
				//System.out.println("Going left.." + root.rightSum);
				sumOfDistances = (root.city.distance - c.distance) + (root.rightSum - root.numRight*c.distance) + search(c,root.leftNode);
				root.leftSum -= c.distance;
				root.numLeft -= 1;
			}else{
				//System.out.println("Going right.." + root.leftSum);
				sumOfDistances = (c.distance - root.city.distance) + (root.numLeft*c.distance - root.leftSum) + search(c,root.rightNode);
				root.rightSum -= c.distance;
				root.numRight -= 1;
			}
			return sumOfDistances;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int numScenarios = s.nextInt();
		for(int i=0;i<numScenarios;i++){
			int numCities = s.nextInt();
			City cities[] = new City[numCities];
			for(int j=0;j<numCities;j++){
				cities[j] = new City();
				cities[j].distance = s.nextLong();
			}
			for(int k=0;k<numCities;k++){
				cities[k].population=s.nextLong();
			}
			Arrays.sort(cities);
			System.out.println(compute(numCities,cities));
		}
		s.close();
	}
	
	public static String compute(int numCities,City cities[]){
		BigInteger globalSum = new BigInteger("0");
		Tree tree = new Tree();
		for(int i=0;i<numCities;i++){
			tree.insert(new Node(cities[i]));
			//System.out.println(sumOfDistances);
		}
		//System.out.println(tree.root);
		for(int j=numCities-1;j>=0;j--){
			globalSum = globalSum.add((new BigInteger(Long.toString(cities[j].population))).multiply(new BigInteger(Long.toString(tree.search(cities[j])))));
		}
		return globalSum.mod(new BigInteger("1000000007")).toString();		
	}

}