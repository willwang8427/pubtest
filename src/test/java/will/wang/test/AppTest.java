package will.wang.test;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public void testRevertArrayNullOrOne() {
    	int [] array = null;
    	assertTrue(SortedArray.revertArray(array) == null);
    	
    }
    
    public void testRevertArray() {
    	
    	checkRevertArray(0);//检查长度为0的数组
    	checkRevertArray(1);//检查长度为1的数组
    	checkRevertArray(5);//检查长度为奇数的数组
    	checkRevertArray(6);//检查长度为偶数的数组
    }
    
    public void testRevertFile() throws IOException {
    	File inputFile = createTempFile(100);
        File file = SortedArray.revertNumberFile(inputFile);
        assertTrue(file != null);
        
        RandomAccessFile fs = null;
		try {
			fs = new RandomAccessFile(file,"r");
			int result = 99;
			while(true) {
			    try {
				    int num = fs.readInt();
				    assertTrue(result == num);
				    result--;
			    }
			    catch(EOFException e) {
			    	break;
			    }
			}
		}
		finally {
			if(fs != null) {
				try {
				fs.close();
				}
				catch(IOException e) {}
			}
		}
        
        
    }
    
    private void checkRevertArray(int length) {
    	int [] array = new int[length];
    	
    	for(int i=0; i < length; i++) {
    		array[i] = i;
    	}
    	
    	array = SortedArray.revertArray(array);
    	
    	for(int i = 0; i < length; i++) {
    		assertTrue( array[i] == length-1 - i); 
    	}
    }
    
    
    
    
    private File createTempFile(int length) throws IOException {
		String fileName = String.valueOf(System.currentTimeMillis()); 
		File file = new File(fileName);
		if(!file.exists()) {
			file.createNewFile();
		}
		RandomAccessFile fs = null;
		try {
			fs = new RandomAccessFile(file,"rw");
		
			for(int i=0; i<length; i++) {
				fs.writeInt(i);
			}
			return file;
		}
		finally {
			if(fs != null) {
				try {
					fs.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
    
    public void testFib() throws IOException {
    	 Fib.fibLoop(1000);
    	
      	//System.out.println("testFib 第10000位:" + result);
      	 
    }
    /**
     * 比较两种算法的结果是否一致
     * @throws IOException 
     */
    public void testFibMatrixResult() throws IOException {
    	BigInteger result = Fib.fibMatrix(100000);
    	
    	BigInteger result1 = Fib.fibLoop(100000);
    	
    	assertTrue(result.compareTo(result1) == 0);
    	
    	//System.out.println("testMatrix 第10000位:" + result);
    }
    
    /**
     * 计算很大数据
     */
    public void testFibBigMatrix() {
    	
    	long n = 1000000;
    	
        Fib.fibMatrix(n);
    	//System.out.println("testMatrix 第" + n +   "位:" + result);
    }
    
    /**
     * Test Find N
     */
    public void testFindN() {
    	//10^100000  最小的100万位数
    	BigInteger b = BigInteger.valueOf(10).pow(100000);
    	
    	long n = Fib.findN(b).longValue(); //第n项开始，比b大
    	
    	
    	BigInteger r1 = Fib.fibMatrix(n);  //计算fib（n）
    	
    	BigInteger r2 = Fib.fibMatrix(n - 1); //计算fib（n-1）
    	
    	assertTrue(r1.compareTo(b) > 0);  // fib（n） > b
    	
    	assertTrue(r2.compareTo(b) < 0); // fib(n - 1) < b
    	
    	System.out.println(n);
    }
    
    
}
