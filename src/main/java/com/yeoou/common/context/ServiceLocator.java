package com.yeoou.common.context;

import org.springframework.context.ApplicationContext;

import com.yeoou.common.context.ServiceLocator;

/**
 * 
 * <p>
 * Title: ServiceLocator
 * </p>
 * <p>
 * Description: Used to get a service been instance by the name of service bean you want to get.
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2007-01-24
 */
public final class ServiceLocator {
	private static ApplicationContext context = null;
	
	/**
	 * @return ApplicationContext
	 */
	protected static ApplicationContext getContext() {
		return ServiceLocator.context;
	}

	/**
	 * @param context ApplicationContext
	 */
	protected static void setContext(ApplicationContext context) {
		ServiceLocator.context = context;
	}	
	
	/**
	 * Creates a new ServiceLocator object.
	 */
	private ServiceLocator() {
	}

	/**
	 * get manager service
	 * 
	 * @param service
	 *          service name
	 * 
	 * @return manager service
	 */
	public static Object getService(final String service) {
		return context.getBean(service);
	}
}
