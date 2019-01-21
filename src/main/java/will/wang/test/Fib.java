package will.wang.test;

import java.math.BigInteger;

public class Fib {

	/**
	 * 基于循环的算法，效率一般 o(n)
	 * @param n
	 * @return
	 */
	public static BigInteger fibLoop(long n) {
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
			
		//	System.out.println("第" + i + "位:" + result);
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
}
