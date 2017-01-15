package com.yeoou.common.context;

import java.util.Calendar;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.acegisecurity.context.SecurityContextImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yeoou.common.context.AppSessionListener;
import com.yeoou.common.context.Global;
import com.yeoou.common.context.ServiceLocator;
//import com.yeoou.components.acegi.ICurrentUserInfoProvider;
//import com.yeoou.rbac.model.RbacAccessLog;
//import com.yeoou.rbac.service.RbacAccessLogManager;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * <p>
 * Title: AppSessionListener.java
 * </p>
 * <p>
 * Description: SessionAttribute listener,used to control the longin of user.
 * </p>
 * 
 * @author kensin
 * @version 1.0
 * @created 2007-12-16
 */
public class AppSessionListener implements HttpSessionAttributeListener, HttpSessionListener {
	private static final Log log = LogFactory.getLog(AppSessionListener.class);
	
//	private RbacAccessLogManager rbacAccessLogManager;
	
	//private ICurrentUserInfoProvider currentUserInfoProvider;

	private static boolean logonStatus = true;

	public void attributeAdded(HttpSessionBindingEvent event) {
		log.info("◆◆attributeAdded::(HttpSessionBindingEvent)arg0.getValue()="+event.getValue());
		log.info("◆◆attributeAdded::(HttpSessionBindingEvent)arg0.getName()="+event.getName());
	/*	
		//Log the user logging information into the database.
	//	if (event.getName().equals(Global.APP_USER_LOGIN_INFO) && event.getValue() instanceof RbacAccessLog) {
	//		RbacAccessLog rbacAccessLog = (RbacAccessLog) event.getValue();
	//		rbacAccessLog.setGroupId(getCurrentUserInfoProvider().getGroupId());
			
		//	getRbacAccessLogManger(event.getSession().getServletContext()).save(rbacAccessLog);
	//	} else if(event.getValue() instanceof SecurityContextImpl) {//authentication != null && 
//			if (!userHashtable.containsKey(authentication.getName())) {
//				logonStatus = true;
//				log.info("Loger name:" + authentication.getName()+", the first log");
//				//((RbacUserDetail)authentication.getPrincipal()).setAddress(arg0.getSession().getServletContext().getRealPath());
//				//userHashtable.put(authentication.getName(), authentication.getPrincipal());
//			} else {
//				logonStatus = false;
//				log.info("this user:" + authentication.getName() + "has logged on somewhere else.");
//				//userHashtable.put(authentication.getName(), authentication.getPrincipal());
//			}
			
//			SecurityContextImpl securityContext = (SecurityContextImpl)event.getValue();
//			Authentication authentication = securityContext.getAuthentication();
//			RbacUserDetail rbacUserDetail = (RbacUserDetail)authentication.getPrincipal();
//			
//			RbacAccessLog rbacAccessLog = new RbacAccessLog();
//			rbacAccessLog.setUserId(rbacUserDetail.getUserId());//authentication.getName()
//			rbacAccessLog.setGroupId(rbacUserDetail.getGroupId());
//			rbacAccessLog.setLogonTimestamp(rbacUserDetail.getLoginTime());
//			rbacAccessLog.setRemoteAddress(((WebAuthenticationDetails)authentication.getDetails()).getRemoteAddress());
//			
//			rbacUserDetail.setAddress(((WebAuthenticationDetails)authentication.getDetails()).getRemoteAddress());
//			//rbacUserDetail.setAccessLogId(((WebAuthenticationDetails)authentication.getDetails()).getRemoteAddress());
//			
//			getRbacAccessLogManger(event.getSession().getServletContext()).save(rbacAccessLog);
			
			log.info("◆◆System.getProperty(acegi.security.strategy)==="+System.getProperty("acegi.security.strategy"));
			//userHashtable.put(((WebAuthenticationDetails)authentication.getDetails()).getSessionId(), authentication);
		}*/
	}

	public static boolean getLogonStatus() {
		return logonStatus;
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		log.info("◆◆attributeRemoved::(HttpSessionBindingEvent)arg0.getValue()="+event.getValue());
		log.info("◆◆attributeRemoved::(HttpSessionBindingEvent)arg0.getName()="+event.getName());
	/*	if(event.getValue() instanceof SecurityContextImpl) {//authentication != null &&   can't include this condition, Because it is the possible that the reponding time has been timed off , so the authentication is null.
			//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			//userHashtable.remove(authentication.getName());
			//userHashtable.remove(((WebAuthenticationDetails)authentication.getDetails()).getSessionId());
			//log.info(((WebAuthenticationDetails)authentication.getDetails()).getRemoteAddress()+" user:" + authentication.getName() + " has logged off!");
		} else if (event.getName().equals(Global.APP_USER_LOGIN_INFO) 
				&& event.getValue() instanceof RbacAccessLog) {//Log the unlogged information into the datebase
			log.info("◆◆Update login info:" + event.getValue());
			RbacAccessLog rbacAccessLog = (RbacAccessLog) event.getValue();
			rbacAccessLog.setLogoffTimestamp(Calendar.getInstance());
			
			getRbacAccessLogManger(event.getSession().getServletContext()).save(rbacAccessLog);

		}*/
		
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		
	}
	
	/**
	 * Initialize the logging manager.
	 * 
	 * @param event Session
	 */
/*	private synchronized void initRbacAccessLogManager(ServletContext context) {
		Map beanMap = WebApplicationContextUtils.getWebApplicationContext(context).getBeansOfType(RbacAccessLogManager.class);

		if (beanMap.isEmpty())
			throw new NoSuchBeanDefinitionException(RbacAccessLogManager.class,
					"Can't write logoff information to database, because the bean of type IRbacAccessLogManager couldn't be found.");

		this.rbacAccessLogManager = (RbacAccessLogManager) beanMap.get(beanMap.keySet().toArray()[0]);
	}
	
	/**
	 * Get the logging manager
	 * 
	 * @param event Session
	 * @return 
	 */
	/*
	private RbacAccessLogManager getRbacAccessLogManger(ServletContext context) {
		if (null == rbacAccessLogManager) {
			initRbacAccessLogManager(context);
		}

		return this.rbacAccessLogManager;
	}
	
	private ICurrentUserInfoProvider getCurrentUserInfoProvider() {
		if (null == currentUserInfoProvider) {
			currentUserInfoProvider = (ICurrentUserInfoProvider) ServiceLocator.getService(Global.ACEGI_CURRENTUSERINFOPROVIDER);
		}

		return this.currentUserInfoProvider;
	}*/

	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("◆◆◆◆sessionCreated::arg0.getSource()="+arg0.getSource());
		
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("◆◆◆◆sessionDestroyed::arg0.getSource()="+arg0.getSource());		
	}
}
