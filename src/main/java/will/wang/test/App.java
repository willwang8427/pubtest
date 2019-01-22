package will.wang.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import ch.obermuhlner.math.big.BigDecimalMath;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	//BigDecimal b = Fib.findN(BigDecimal.valueOf(10).pow(100000));
    	
    	//Fib.fibLoop(1000);
    	
    	for(int i=1; i< 7; i++) {
    		BigInteger num = BigInteger.valueOf(10).pow(i);
    		BigInteger n1 = Fib.findN(num);
    		System.out.println("第" + n1 + " > " + num );
    	}
    	
    	BigInteger a = BigInteger.valueOf(10).pow(100000);
		BigInteger n1 = Fib.findN(a);
		System.out.println("第" + n1 + " > "  );
		
		long c = n1.longValue();
		System.out.println("c = " + c);
		BigInteger r = Fib.fibMatrix(c);
		
		BigInteger x = Fib.fibMatrix(c -1);
		
		if(r.compareTo(a) > 0) {
			System.out.println("yes");
		}
		
		if(x.compareTo(a) < 0) {
			System.out.println("yes 2");
		}
    	
    
        System.out.println( "Hello World!"  );
    }
    
    
    
    
}
