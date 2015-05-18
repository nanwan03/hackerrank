import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	public static void main(String[] args) {
        
        Scanner cin=new Scanner(System.in);
        int N=cin.nextInt();
        int I=cin.nextInt();
        ArrayList<HashSet<Integer>> ahi=new ArrayList<HashSet<Integer>>();
        int a,b;
        for(int i=0;i<I;i++){
        	a=cin.nextInt();
        	b=cin.nextInt();
        	boolean flag=true;
        	int meet=0;
        	HashSet<Integer> hs1=new HashSet<Integer>();
        	HashSet<Integer> hs2=new HashSet<Integer>();
        	for(HashSet<Integer> hs:ahi){
        		
        		if(hs.contains(a)||hs.contains(b)){
        			if(meet==0){
        				flag=false;
        				hs.add(a);
        				hs.add(b);
        				hs1=hs;
        				meet=1;
        			}
        			else{
        				hs2=hs;
        				meet=2;
        			}
        		}
        	}
        	if(meet==2){
        		hs1.addAll(hs2);
        		ahi.remove(hs2);
        	}
        	if(flag){
        		HashSet<Integer> hs=new HashSet<Integer>();
        		hs.add(a);
        		hs.add(b);
        		ahi.add(hs);
        	}
        }
        long res=(long)N*(long)(N-1)/2L;
        long minus=0L;
        for(HashSet<Integer> hs:ahi){
    		minus+=(long)hs.size()*(long)(hs.size()-1)/2;
    	}
        System.out.println(res-minus);
    }
}

