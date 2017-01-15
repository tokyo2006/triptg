package com.yeoou.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author calvin
 */
public class WebUtils {

	private WebUtils() {
	}

	/**
	 * 重载Spring WebUtils中的函数,作用如函数名所示 加入泛型转换,改变输入参数为request 而不是session
	 *
	 * @param name  session中变量名称
	 * @param clazz session中变量的类型
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getOrCreateSessionAttribute(HttpServletRequest request, String name, Class<T> clazz) {
		return (T) org.springframework.web.util.WebUtils.getOrCreateSessionAttribute(request.getSession(), name, clazz);
	}
}
