
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;


public class Solution {
	
	static class Array{
		TreeMap<Long, Integer> map = new TreeMap<Long, Integer>();
		int gpos, pos, len;
		long median;
		
		public void insert(long num){
			Integer val = map.get(num);
			if(val == null){
				map.put(num, 1);
			}else{
				map.put(num, val+1);
			}
			
			len++;
			
			if(len == 1){
				median = num;
				gpos = 0;
				pos = 0;
			}else{
				if(num<median){
					gpos++;
				}
				int mpos = (len-1)/2;
				move(mpos-gpos);
			}
			printMedian();
		}
		
		public void remove(long num){
			Integer val = map.get(num);
			if(val == null){
				System.out.println("Wrong!");
			}else{
				if(val == 1) map.remove(num);
				else map.put(num, val-1);
				
				if(num < median){
					gpos--;
				}else if(num == median){
					if(val == 1){
						Entry<Long, Integer> entry = map.lowerEntry(num);
						if(entry == null) {
							entry = map.higherEntry(num);
							if(entry != null){
								median = entry.getKey();
								pos = 0;
							}
						}else{
							gpos--;
							median = entry.getKey();
							pos = entry.getValue() - 1;
						}
					}else{
						if(pos == val-1){
							pos--;
							gpos--;
						}
					}
				}
				len--;
				if(len > 0){
					int mpos = (len-1)/2;
					move(mpos-gpos);
					printMedian();
				}else{
					System.out.println("Wrong!");
				}
			}
		}
		
		public void move(int n){
			if(n==0) return;
			else if(n==1){
				int val = map.get(median);
				if(pos+1<val){
					pos++;
					gpos++;
				}else{
					pos = 0;
					gpos++;
					median = map.higherKey(median);
				}
			}else if(n==-1){
				if(pos-1 >= 0){
					pos--;
					gpos--;
				}else{
					Entry<Long,Integer> entry = map.lowerEntry(median);
					pos = entry.getValue()-1;
					gpos--;
					median = entry.getKey();
				}
			}else{
				throw new RuntimeException("Error: move by " + n);
			}
			
		}
		
		public void printMedian(){
			if((len&1) == 1){
				System.out.println(median);
			}else{
				long sum = median;
				move(1);
				sum += median;
				move(-1);
				
				if(sum==-1){
					System.out.println("-0.5");
				}else{
					System.out.print(sum/2);
					if((sum&1)==1){
						System.out.print(".5");
					}
					System.out.println();
				}
			}
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Array ar = new Array();
		for(int i=0; i<N; i++){
			char c = sc.next().charAt(0);
			long num = sc.nextLong();
			
			if(c=='a'){
				ar.insert(num);
			}else{
				ar.remove(num);
			}
		}
	}

}

