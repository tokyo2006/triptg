package com.yeoou.common.utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 * <p>
 * 标题: 文件工具
 * </p>
 * <p>
 * 描述: 为文件读写提供一些封装
 * </p>
 * 
 * @author kensin
 * @version 1.0
 * @created 2008-12-16
 */
public class FileUtils
{
	/**
	 * 删除文件
	 * @param file	目标文件
	 */
	 public static void removeFile(File file) {
	        int maxTry = 3;
	        while(maxTry>0) {
	            maxTry--;
	            if(file.isFile()) {
	                if(file.delete())
	                    return;
	                else
	                    continue;
	            }
	            else {
	                return;
	            }
	        }
	    }
	 /**
	  * 读文件
	  * @param file				目标文件
	  * @param output			输出流
	  * @throws IOException		输入输出异常
	  */
	    public static void readFile(File file, OutputStream output) throws IOException {
	        FileInputStream input = null;
	        FileChannel fc = null;
	        try {
	            input = new FileInputStream(file);
	            fc = input.getChannel();
	            ByteBuffer buffer = ByteBuffer.allocate(4096);
	            for(;;) {
	                buffer.clear();
	                int n = fc.read(buffer);
	                if(n==(-1))
	                    break;
	                output.write(buffer.array(), 0, buffer.position());
	            }
	        }
	        finally {
	            if(fc!=null) {
	                try {
	                    fc.close();
	                }
	                catch(IOException e) {}
	            }
	            if(input!=null) {
	                try {
	                    input.close();
	                }
	                catch(IOException e) {}
	            }
	        }
	    }
	    /**
	     * 写文件
	     * @param file			目标文件
	     * @param data			要写的数据
	     * @throws IOException	输入输出异常
	     */
	    public static void writeFile(File file, byte[] data) throws IOException {
	        final int MAX_BUFFER_SIZE = 4096;
	        FileOutputStream output = null;
	        FileChannel fc = null;
	        try {
	            output = new FileOutputStream(file);
	            fc = output.getChannel();
	            ByteBuffer buffer = ByteBuffer.allocate(MAX_BUFFER_SIZE);
	            int offset = 0;
	            while(offset<data.length) {
	                buffer.clear();
	                int len = data.length - offset;
	                if(len>MAX_BUFFER_SIZE)
	                    len = MAX_BUFFER_SIZE;
	                buffer.put(data, offset, len);
	                offset += len;
	                buffer.flip();
	                fc.write(buffer);
	            }
	        }
	        finally {
	            if(fc!=null) {
	                try {
	                    fc.close();
	                }
	                catch(IOException e) {}
	            }
	            if(output!=null) {
	                try {
	                    output.close();
	                }
	                catch(IOException e) {}
	            }
	        }
	    }
	    /**
	     * 保存上传的文件
	     * @param file			目标文件
	     * @param savePath		目标文件路径
	     * @param fileName		文件名称
	     * @throws IOException	输入输出异常
	     */
	    public static void UploadFile(File file ,String savePath,String fileName)throws IOException
	    {
	    	File dir = new File(savePath);
	    	if(!dir.exists())
	    	{
	    		dir.mkdirs();
	    	}
    		FileOutputStream fos= new FileOutputStream(savePath+"\\"+fileName);
    		FileInputStream fis = new FileInputStream(file);
    		byte[] buffer = new byte[1024];
			int len = 0;
			while((len=fis.read(buffer))>0)
			{
				fos.write(buffer,0,len);
			}
	    }
//	    public static String getWordDocument(String filepath) {   
//	        StringBuffer sb = new StringBuffer();   
//	        try {   
//	            InputStream is = new FileInputStream(filepath);   
//	            WordExtractor ex = new WordExtractor(is);// is是WORD文件的InputStream   
//	            sb.append(ex.getText());   
//	        } catch (Exception e) {   
//	            e.printStackTrace();   
//	        }   
//	        return sb.toString();   
//	    }
//	    public static String getExcelDocument(String filepath) {   
//	        StringBuffer sb = new StringBuffer();   
//	        try {   
//	            InputStream is = new FileInputStream(filepath);   
//	            HSSFWorkbook workbook = new HSSFWorkbook(is);// 创建对Excel工作簿文件的引用   
//	            for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {   
//	                if (null != workbook.getSheetAt(numSheets)) {   
//	                    HSSFSheet aSheet = workbook.getSheetAt(numSheets);// 获得一个sheet   
//	                    for (int rowNumOfSheet = 0; rowNumOfSheet <= aSheet   
//	                            .getLastRowNum(); rowNumOfSheet++) {   
//	                        if (null != aSheet.getRow(rowNumOfSheet)) {   
//	                            HSSFRow aRow = aSheet.getRow(rowNumOfSheet); // 获得一个行   
//	                            for (short cellNumOfRow = 0; cellNumOfRow <= aRow   
//	                                    .getLastCellNum(); cellNumOfRow++) {   
//	                                if (null != aRow.getCell(cellNumOfRow)) {   
//	                                    HSSFCell aCell = aRow.getCell(cellNumOfRow);// 获得列值   
//	                                    sb.append(aCell.getStringCellValue());   
//	                                }   
//	                            }   
//	                        }   
//	                    }   
//	                }   
//	            }   
//	        } catch (Exception e) {   
//	            System.out.println("已运行xlRead()  :  " + e);   
//	        }   
//	        return sb.toString();   
//	    }  
}
