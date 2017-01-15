package com.yeoou.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yeoou.common.model.Genericmodel;
/**
 * <p>
 * Title: 操作Cookie组件
 * </p>
 * <p>
 * Description: 获取，建立，删除cookie信息
 * </p>
 * 
 * @author kensin
 * @version 1.0
 * @created 2009-04-8
 */
public class CookieUtil extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	/**
	 * 获取路径
	 * @param request
	 * @return	返回路径信息
	 */
	private static String getPath(HttpServletRequest request)
	{
		String path = request.getContextPath();
		return (path == null || path.length()==0)?"/":path;
	}
	/**
	 * 获取request相关cookie路径
	 * @param request	网页请求
	 * @param name		cookie名称
	 * @return	获取指定的cookie信息
	 */
	public static Cookie getCookie(HttpServletRequest request, String name)
	{
		 Cookie cookies[] = request.getCookies();
		 if(cookies == null || name == null || name.length() == 0)
		 {
			 return null;
		 }
		 for(int i=0;i<cookies.length;i++)
		 {
			 //应该是与的
			 if (name.equals(cookies[i].getName())|| request.getServerName().equals(cookies[i].getDomain()))
			 {
				 return cookies[i];
			 }
		 }
		 return null;
	}
	/**
	 * 删除cookie信息
	 * @param request	网页请求
	 * @param response	网页响应
	 * @param cookie	要删除的cookie
	 */
	public static void deleteCookie(HttpServletRequest request,HttpServletResponse response, Cookie cookie)
	{
		    if (cookie != null) 
		    {
		      cookie.setPath(getPath(request));
		      cookie.setValue("");
		      cookie.setMaxAge(0);
		      response.addCookie(cookie);
		    }
	}
	/**
	 * 添加一条cookie信息
	 * @param request		网页请求
	 * @param response		网页响应
	 * @param name			cookie名称
	 * @param value			cookie内容
	 */
	public static void setCookie(HttpServletRequest request,HttpServletResponse response, String name, String value) 
	{
		    setCookie(request, response, name, value, 0x278d00);
	}
	/**
	 * 添加一条cookie信息
	 * @param request		网页请求
	 * @param response		网页响应
	 * @param name			cookie名称
	 * @param value			cookie内容
	 * @param maxAge		保存时间
	 */
	public static void setCookie(HttpServletRequest request,HttpServletResponse response, String name, String value, int maxAge)
	{
		    Cookie cookie = new Cookie(name, value == null ? "" : value);
		    cookie.setMaxAge(maxAge);
		    cookie.setPath(getPath(request));
		    response.addCookie(cookie);
	}
}
