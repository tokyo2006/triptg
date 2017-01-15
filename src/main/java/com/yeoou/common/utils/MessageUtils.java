package com.yeoou.common.utils;

import java.util.Locale;

import javax.servlet.jsp.PageContext;

import org.apache.struts2.views.jsp.TagUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.ValueStackFactory;
/**
 * 本地化消息工具
 * @author kensin
 *
 */
public class MessageUtils {

	public static ValueStack stack = ValueStackFactory.getFactory().createValueStack();
	
	public static String findValue(String expr, Locale locale) {
		return (String)stack.findValue(expr, String.class);
	}
	
	public static String findValue(String expr, PageContext pageContext) {
		ValueStack stack = ActionContext.getContext().getValueStack();//TagUtils.getStack(pageContext);
		return (String)stack.findValue(expr, String.class);
	}
	
	
}
