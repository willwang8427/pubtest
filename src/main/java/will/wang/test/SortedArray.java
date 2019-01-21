package will.wang.test;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 问题：存在一个有序数列倒叙，要求空间复杂度为常数
 * @author willwang
 *
 */
public class SortedArray {
	
	
	
	/**
	 * 假设以数组保存该有序数列，则反转数组即可,原数组被修改
	 * 原数组会被反转，时间复杂度为o（n/2），空间复杂度为常数o（1）
	 * Test case - 
	 * @param sortedArray
	 * @return 反转后的数组
	 * 
	 */
	public static int[] revertArray(int[] sortedArray) {
		
		if(sortedArray == null || sortedArray.length < 2) {
			return sortedArray;
		}
		int size = sortedArray.length;
		int mid = size >> 1;
		
		int temp;
		
		for(int i=0, j = size - 1; i < mid; i++,j--) {
			temp = sortedArray[i];
			sortedArray[i] = sortedArray[j];
			sortedArray[j] = temp;
		}	
		return sortedArray;
	}
	

	/**
	 * 假设输入数据保存在很大的文件，则使用读-写文件的形式，实现反转
	 * 该实现借助文件实现存储，因此，空间复杂度为o（1），时间复杂度为o（n）
	 * 
	 * Test case -  testRevertFile
	 * @param f - 源文件或支持以光标读取的数据源 此处为文件
	 * @return - 以文件形式返回，提供结果
	 * @throws IOException
	 * 
	 */
	public static File revertNumberFile(File f) throws IOException {
		if(f != null && f.exists() && f.isFile() && f.canRead()) {
		    
			RandomAccessFile inputStream = null;
			RandomAccessFile fs = null;
			
			try {
				inputStream = new RandomAccessFile(f,"r");
				
				long length = inputStream.length(); //注意，必须知道具体的输入源大小，否则不能确定反转时光标位置
			
				String fileName = String.valueOf(System.currentTimeMillis()); 
				File file = new File(fileName);
				if(!file.exists()) {
					file.createNewFile();
				}
		    
				fs = new RandomAccessFile(file,"rw");
		    
				long pos = length - 4;
				while(pos >= 0) {
					try {
						int num = inputStream.readInt();
						fs.seek(pos); //查找反转后的位置
						fs.writeInt(num);
						pos -= 4;
					}catch(EOFException eof) {
						break;
					}
				}
				
				return file;
			}
			finally {
				if(inputStream != null) {
					try {
						inputStream.close();
					}
					catch(Exception e) {}
				}
				if(fs != null) {
					try {
					    fs.close();
					}
					catch(Exception e) {}
				}
			}
		    
		}
		return null;
	}

}
