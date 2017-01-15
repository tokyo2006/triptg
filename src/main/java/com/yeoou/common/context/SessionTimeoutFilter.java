package com.yeoou.common.context;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yeoou.common.context.Global;
import com.yeoou.common.context.SessionTimeoutFilter;

/**
 * @deprecated
 * <p>
 * Title: SessionTimeoutFilter
 * </p>
 * <p>
 * Description: 
 * When the session destroyed, MySessionListener will do necessary logout
 * operations. Later, at the first request of client, this filter will be fired
 * and redirect the user to the appropriate timeout page if the session is not
 * valid.
 * </p>
 * @author Wansong
 * @version 1.0
 * @created 2007-01-24
 */
public class SessionTimeoutFilter implements Filter {
	private final Log logger = LogFactory.getLog(SessionTimeoutFilter.class);

	//the page name to be redirected when the session times out.
	private String timeoutPage = "login.jsp";

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		if ((request instanceof HttpServletRequest)
				&& (response instanceof HttpServletResponse)) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;

			logger.info("◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆SessionTimeoutFilter.doFilter:"
					+ httpServletRequest.getRequestURI());

			// is session expire control required for this request?
			if (isSessionControlRequiredForThisResource(httpServletRequest)) {

				// is session invalid?
				if (isSessionInvalid(httpServletRequest)) {
					String timeoutUrl = httpServletRequest.getContextPath() + "/" + getTimeoutPage();
					logger.info("session is invalid! redirecting to timeoutpage : " + timeoutUrl);
					if(isAjaxRequest(httpServletRequest)) {
						httpServletResponse.setHeader(Global.AJAX_REQUEST_TIMEOUT, "true");
						httpServletResponse.setHeader(Global.AJAX_REQUEST_TIMEOUT_REDIRECT_URL, httpServletRequest.getContextPath() + "/" + "login.jsp");
					} else {
						httpServletResponse.sendRedirect(timeoutUrl);
					}
					
					return;
				}
			}
		}

		filterChain.doFilter(request, response);
	}

	/**
	 * 
	 * Session shouldn't be checked for some pages. For example: for timeout
	 * page.. Since we're redirecting to timeout page from this filter, if we
	 * don't disable session control for it, filter will again redirect to it
	 * and this will be result with an infinite loop...
	 */
	private boolean isSessionControlRequiredForThisResource(
			HttpServletRequest httpServletRequest) {
		String requestPath = httpServletRequest.getRequestURI();

		boolean controlRequired = !(StringUtils.contains(requestPath,
				"login.jsp")
				|| StringUtils.contains(requestPath, "login.jsp")
				|| StringUtils.contains(requestPath,
						"rbac_acegi_security_check") || StringUtils.contains(
				requestPath, "logout"));

		return controlRequired;
	}

	private boolean isSessionInvalid(HttpServletRequest httpServletRequest) {
		logger.info("◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆SessionTimeoutFilter.isSessionInvalid:httpServletRequest.getRequestedSessionId()="
						+ httpServletRequest.getRequestedSessionId()
						+ ", httpServletRequest.isRequestedSessionIdValid()="
						+ httpServletRequest.isRequestedSessionIdValid());
		boolean sessionInValid = (httpServletRequest.getRequestedSessionId() != null)
				&& !httpServletRequest.isRequestedSessionIdValid();
		return sessionInValid;
	}

	public void destroy() {
	}

	public String getTimeoutPage() {
		return timeoutPage;
	}

	public void setTimeoutPage(String timeoutPage) {
		this.timeoutPage = timeoutPage;
	}

	/**
	 * Judge whether the current request is ajax request or not.
	 * @param request
	 * @return boolean 
	 *                If the current request is ajax request , return true, or return false.
	 */
	private boolean isAjaxRequest(HttpServletRequest request) {
		return request.getParameter("ajax") != null;
	}

}