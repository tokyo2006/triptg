package com.yeoou.common.web;


import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.User;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yeoou.common.context.ServiceLocator;
import com.yeoou.rbac.service.IUserService;
import com.yeoou.rbac.service.impl.UserService;
import com.yeoou.tour.model.Corporation;
import com.yeoou.tour.model.Manager;
import com.yeoou.tour.service.IIPRouterService;
import com.yeoou.tour.service.impl.IPRouterService;

/**
 * <p>
 * Title: BaseActionSupport
 * </p>
 * <p>
 * Description: Base Action class.
 * </p>
 * 
 * @author kensin
 * @version 1.0
 * @created 2007-11-22
 */
public class BaseActionSupport extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -377353184604705991L;
	
	private long totalCount = 0;// 总数
	/**
	 * 获取用户名
	 * @return
	 */
	protected String  getGlobalUserName()
	{
		SecurityContext context = SecurityContextHolder.getContext();
		if(context!=null)
		{
			Authentication authentication = context.getAuthentication();
			User ud =(User)authentication.getPrincipal();
			return ud.getUsername();
		}
	    return null;
	}
	protected Corporation getCorporationInfoByManager()
	{
		IUserService userService = (UserService)ServiceLocator.getService("userService");
		Corporation corp = new Corporation();
		if(this.getManagerInfo()!=null)
		{
			corp = userService.getCorpByUserId(this.getManagerInfo().getBossId());
		}
		else
		{
			return null;
		}
		return corp;
	}
	protected String getIPAddress()
	{
		String address = ServletActionContext.getRequest().getRemoteAddr();
		IIPRouterService iprouterService = (IPRouterService)ServiceLocator.getService("iprouterService");
		return iprouterService.getAreaIdByIP(address);
	}
	protected Manager getManagerInfo()
	{
		IUserService userService = (UserService)ServiceLocator.getService("userService");
		com.yeoou.rbac.model.User user = userService.getUserByName(this.getGlobalUserName());
		Manager manager = new Manager();
		if(user!=null)
		{
			manager = userService.getManagerByUserId(user.getUserId());
		}
		else
		{
			return null;
		}
		return manager;
	}
	public String execute() throws Exception {
		
        return SUCCESS;
    }
	public long getTotalCount()
	{
		return totalCount;
	}

	public void setTotalCount(long totalCount)
	{
		this.totalCount = totalCount;
	}
	

}
