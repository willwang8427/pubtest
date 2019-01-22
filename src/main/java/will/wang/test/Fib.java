package will.wang.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import ch.obermuhlner.math.big.BigDecimalMath;

public class Fib {

	/**
	 * 基于循环的算法，效率一般 o(n)
	 * @param n
	 * @return
	 * @throws IOException 
	 */
	public static BigInteger fibLoop(long n){
		if(n <= 0) {
			throw new IllegalArgumentException("please make sure n > 0");
		}
		if(n < 3) {
			return BigInteger.valueOf(1);
		}
		BigInteger f1 = BigInteger.valueOf(1);
		BigInteger f2 = BigInteger.valueOf(1);
		BigInteger result = null;	
		
		for(long i=3; i <= n; i++) {
			result = f1.add(f2);
			f1 = f2;
			f2 = result;
			
	    	//System.out.println(result);
		}
		
		return result;
	}
	
	/**
	 * https://en.wikipedia.org/wiki/Fibonacci_number
	 * 
	 *  [F(n+1) F(n)]    [1  1 ]  ^n
     * |              |=|       |
     *  [F(n) F(n-1)]    [1  0 ]

	 *  通过数学公式，将线性加法运算转换成矩阵幂运算（加法转成乘法）。
	 *  根据快速幂运算，实现该算法（累乘转换成幂乘）
	 *  通过testcase检测，计算第1000000位的斐波那契数列值，大约耗去1.8s
	 * @return
	 */
	public static BigInteger fibMatrix(long n) {
		if(n <= 0) {
			throw new IllegalArgumentException("please make sure n > 0");
		}
		if(n < 3) {
			return BigInteger.valueOf(1);
		}
		/**
		 * 底数
		 */
		BigInteger [][] base = new  BigInteger[][] {
			{BigInteger.valueOf(1),BigInteger.valueOf(1)},
			{BigInteger.valueOf(1),BigInteger.valueOf(0)}
		};
		
		/**
		 * 用来保存最终结果，初始值为元矩阵
		 */
		BigInteger [][] result = new BigInteger [][] {
			{BigInteger.valueOf(1),BigInteger.valueOf(0)},
			{BigInteger.valueOf(0),BigInteger.valueOf(1)}
		};
		
		while(n > 0) {
			if( (n & 1) == 1) { 
				result = matrixMultply(result,base);
			}
			base = matrixMultply(base,base);
			
			n>>=1;
		}
		return result[0][1];
	}
	
	private static BigInteger[][] matrixMultply(BigInteger[][] a,BigInteger[][] b){
		
		BigInteger [][] c = new BigInteger [][] {
			{BigInteger.valueOf(0),BigInteger.valueOf(0)},
			{BigInteger.valueOf(0),BigInteger.valueOf(0)}
		};;
		for(int i = 0; i < 2; i++) {
			for(int j=0; j < 2; j++) {
				for(int k=0; k< 2; k++) {
					c[i][j] = c[i][j].add( a[i][k].multiply(b[k][j]) );
				}
			}
		}
		
		return c;
	}
	
	
	/**
	 * 从序号N开始，数列值开始大于l
	 * 
	 * 来源于： https://math.stackexchange.com/questions/67707/how-many-numbers-are-in-the-fibonacci-sequence
	 * 
	 * @param l
	 * @return - 数列序号
	 */
	 public static long findN(long l) {
	    double d = l;
	    	
	    double d1 = (d + 0.5) * Math.sqrt(5d);
	    	
	    double d2 = (1 + Math.sqrt(5d)) * 0.5;
	    	
	    return (long) (Math.log(d1)/Math.log(d2) + 1);
	    	
	}
	    
	private static MathContext mathContext = MathContext.DECIMAL128;
	/**
	 * 大数查找序号
	 * 	
	 * @param l
	 * @return
	 */
	public static BigInteger findN(BigInteger l) {
			 BigDecimal d = new BigDecimal(l);
			    	
			 BigDecimal d1 =  d.add(new BigDecimal("0.5"))
					 .multiply(new BigDecimal(String.valueOf(Math.sqrt(5d)))
							 );
			    	
			 BigDecimal d2 = new BigDecimal(String.valueOf((1 + Math.sqrt(5d)) * 0.5));
			 
			 return BigDecimalMath.log(d1, mathContext).divide(
					 BigDecimalMath.log(d2, mathContext),
					 mathContext
					 ).add(new BigDecimal(1))
			 .toBigInteger();		    	
	}
	    
}
