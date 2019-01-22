package will.wang.test;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	//10^0 = 1  10^1 =10  10^2 = 100, 10^3 = 1000, 10^4 = 10000,...
    	//故，最小n位的数字，为10^(n-1) 
    	
    	//10^999 为第一个包含 1000 位的最小数字
    	BigInteger b = BigInteger.valueOf(10).pow(999);
    	
    	System.out.println("最小的1000个数字的数是 10^999：" + b);
    	
    	long n = Fib.findN(b).longValue(); //第n项开始，比b大
    	
    	BigInteger r1 = Fib.fibMatrix(n);  //计算fib（n）
    	
    	BigInteger r2 = Fib.fibMatrix(n - 1); //计算fib（n-1）
    	
    	if(r1.compareTo(b) > 0) {  // fib（n） > b
    	    System.out.println("数列第" + n +  "项的值比 10^999大" );
    	}
    	
    	if(r2.compareTo(b) < 0) {
    		System.out.println("数列第" + (n-1) +"项的值比 10^999小" );
    	}
    	

    	System.out.println("因此数列从第" + n +"项开始，值的位数包含1000个数字");
    	
    	
    	//System.out.println(n);
    
        //System.out.println( "Hello World!"  );
    }
    
    
    
    
}
