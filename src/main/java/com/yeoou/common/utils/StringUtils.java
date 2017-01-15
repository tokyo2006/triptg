package com.yeoou.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import com.yeoou.tour.model.KeyWord;

/**
 * <p>
 * 标题: 字符串工具
 * </p>
 * <p>
 * 描述: 对字符串进行额外的处理
 * </p>
 * 
 * @author kensin
 * @version 1.0
 * @created 2008-12-16
 */
public class StringUtils
{
	/**
	 * 对字符串进行转义处理
	 * @param htmlString	需要处理的字符串
	 * @return				处理完毕的字符串
	 */
	
	public static String HtmlEncode(String htmlString)
	{
		if(htmlString!=null)
		{
			htmlString = htmlString.replace(">", "&gt;");
			htmlString = htmlString.replace("<", "&lt;");
			htmlString = htmlString.replace(" ", "&nbsp;");
			htmlString = htmlString.replace("\009", "&nbsp;");
			htmlString = htmlString.replace("\034", "&quot;");
			htmlString = htmlString.replace("\039", "&#39;");
			htmlString = htmlString.replace("\r\n", "<BR />");
			return htmlString;
		}
		else
		{
			return "";
		}
	}
	public static String SimpleHtmlEncode(String htmlString)
	{
		if(htmlString!=null)
		{
			htmlString = htmlString.replace("\r\n", "<BR />");
			return htmlString;
		}
		else
		{
			return "";
		}
	}
	/**
	 * 将IP地址转化外长整型
	 * @param addr	IP地址
	 * @return		长整型数据
	 */
	public static long getIPStringToLong(String addr)
	{
		
		try
		{
			InetAddress ip;
			ip = InetAddress.getByName(addr);
			byte[] b = ip.getAddress();   
	        long l = b[0] << 24L& 0xff000000L | b[1] << 16L & 0xff0000L| b[2] << 8L & 0xff00 | b[3] << 0L & 0xff;   
	        return l;  
		} catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		 
	}
	/**
	 * 
	 * @deprecated
	 * @param content		要替换内容
	 * @param keyWordList	关键字替换列表
	 * @return
	 */
	public static String KeyWordReplace(String content ,List<KeyWord> keyWordList)
	{
		if(keyWordList!=null)
		{
			String temp ="";
			String result = "";
			int size = keyWordList.size();
			for(int i=0;i<size;i++)
			{
				result = "<A href=\""+keyWordList.get(i).getUrl()+"\">"+keyWordList.get(i).getName()+"</A>";
				temp = content.replaceAll(keyWordList.get(i).getName(), result);
				content = temp;
			}
			return content;
		}
		else
		{
			return content;
		}
	}
}
