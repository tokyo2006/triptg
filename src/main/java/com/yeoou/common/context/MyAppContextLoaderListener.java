package com.yeoou.common.context;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yeoou.common.context.MyAppContextLoaderListener;
import com.yeoou.common.context.ServiceLocator;


/**
 * 
 * <p>
 * Title: MyAppContextLoaderListener.java
 * </p>
 * <p>
 * Description: 
 * </p>
 * @author  kensin
 * @version 1.0
 * @created 2007-01-24
 */
public class MyAppContextLoaderListener extends ContextLoaderListener {
	private static final Log log = LogFactory.getLog(MyAppContextLoaderListener.class);

	/*
	 * @see org.springframework.web.context.ContextLoaderListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		ServiceLocator.setContext(null);
		 log.info("ApplicationContext has been destroyed.");
	}

	/*
	 * @see org.springframework.web.context.ContextLoaderListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		ServiceLocator.setContext(WebApplicationContextUtils.getWebApplicationContext(event.getServletContext()));
		 log.info("ApplicationContext has been initialized.");
	}

}
