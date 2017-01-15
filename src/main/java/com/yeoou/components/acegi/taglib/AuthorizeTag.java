package com.yeoou.components.acegi.taglib;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.ExpressionEvaluationUtils;
import com.yeoou.components.acegi.cache.AcegiCacheManager;
import com.yeoou.components.acegi.resource.ResourceDetails;

/**
 * 对用户授权进行认证的Tag, 如果用户没相应授权，则Tag中的Html代码全部不显示
 * @author cac
 */
public class AuthorizeTag extends TagSupport {

	private static final long serialVersionUID = 0L;

	private String res = "";

	// ~ Methods
	// ========================================================================================================

	public int doStartTag() throws JspException {
		ServletContext sc = pageContext.getServletContext();
		WebApplicationContext webAppCtx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(sc);
		AcegiCacheManager acegiCacheManager = (AcegiCacheManager) webAppCtx
				.getBean("acegiCacheManager");
		
		//取出 tag 的名称
		final String tag = ExpressionEvaluationUtils.evaluateString("res", res,
				pageContext);
		
		//找出相应的资源
		ResourceDetails rd = acegiCacheManager.getResourceFromCache(tag);

		Collection required;
		if (rd == null) {
			return Tag.SKIP_BODY;
		}
		
		required = Arrays.asList(rd.getAuthorities());

		
		final Collection granted = getPrincipalAuthorities();
		Collection grantedCopy = copy(granted);
		
		//判断权限
		if ((null != tag) && !"".equals(tag)) {
			grantedCopy.retainAll(required);
			if (grantedCopy.isEmpty()) {
				return Tag.SKIP_BODY;
			}
		}
		return Tag.EVAL_BODY_INCLUDE;
	}

	/**
	 * 获取当前用户授权
	 * @return
	 */
	private Collection getPrincipalAuthorities() {
		Authentication currentUser = SecurityContextHolder.getContext()
				.getAuthentication();
		if (null == currentUser) {
			return Collections.EMPTY_LIST;
		}
		if ((null == currentUser.getAuthorities())
				|| (currentUser.getAuthorities().length < 1)) {
			return Collections.EMPTY_LIST;
		}
		return Arrays.asList(currentUser.getAuthorities());
	}

	private Set copy(Collection c) {
		Set target = new HashSet();
		for (Iterator iterator = c.iterator(); iterator.hasNext();) {
			target.add(iterator.next());
		}
		return target;
	}

	public void setRes(String res) {
		this.res = res;
	}

}
