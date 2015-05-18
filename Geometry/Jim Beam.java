//package AdInfinitumAugust;

import java.util.*;


public class Solution {
	
		static boolean IsOnSegment(double xi, double yi, double xj, double yj,double xk, double yk) {
				return (xi <= xk || xj <= xk) && (xk <= xi || xk <= xj) &&(yi <= yk || yj <= yk) && (yk <= yi || yk <= yj);
			}
	
		static int ComputeDirection(double xi, double yi, double xj, double yj,double xk, double yk) {
				double a = (xk - xi) * (yj - yi);
				double b = (xj - xi) * (yk - yi);
				return a < b ? -1 : a > b ? 1 : 0;
			}

	static boolean DoLineSegmentsIntersect(double x1, double y1, double x2, double y2,double x3, double y3, double x4, double y4) {
			
						int d1 = ComputeDirection(x3, y3, x4, y4, x1, y1);
						int d2 = ComputeDirection(x3, y3, x4, y4, x2, y2);
						int d3 = ComputeDirection(x1, y1, x2, y2, x3, y3);
						int d4 = ComputeDirection(x1, y1, x2, y2, x4, y4);
						return (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) &&
						((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0))) ||
						(d1 == 0 && IsOnSegment(x3, y3, x4, y4, x1, y1)) ||
						(d2 == 0 && IsOnSegment(x3, y3, x4, y4, x2, y2)) ||
						(d3 == 0 && IsOnSegment(x1, y1, x2, y2, x3, y3)) ||
						(d4 == 0 && IsOnSegment(x1, y1, x2, y2, x4, y4));
	}


	static boolean checkIntersection(double x1, double y1, double x2, double y2, double xm, double ym)
	{
		if(DoLineSegmentsIntersect(x1,y1,x2,y2,xm,ym,0,0))
			return false;
		else 
			return true;
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		while(t-- > 0)
		{
			double x1=sc.nextDouble();
			double y1=sc.nextDouble();
			double x2=sc.nextDouble();
			double y2=sc.nextDouble();
			double xm=sc.nextDouble();
			double ym=sc.nextDouble();
			System.out.println(checkIntersection(x1,y1,x2,y2,xm,ym)?"YES":"NO");	
		}

	}

}
