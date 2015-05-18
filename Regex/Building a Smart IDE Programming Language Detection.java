import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static int max=40;

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sr =new Scanner (System.in);
        boolean py_flag=true;
        for(int i=0;i<max;i++) {
        
            String input =sr.nextLine();
            if(input.contains("java")){            
                System.out.println("Java");
                py_flag=false;
                break;
            }
            else
                if(input.contains("stdio")) {            
                    System.out.println("C");
                    py_flag=false;
                    break;
            }
               /* else {
                    System.out.println("Python");
                    return;            
                }  */
        }
        
        if(py_flag)
            System.out.println("Python");
         
    }
}