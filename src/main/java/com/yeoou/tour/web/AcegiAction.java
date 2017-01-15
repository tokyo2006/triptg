package com.yeoou.tour.web;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.web.BaseActionSupport;
import com.yeoou.rbac.model.Role;
import com.yeoou.rbac.model.User;
import com.yeoou.rbac.service.IUserService;
/**
 * <p>
 * Title: 权限功能模块的相关操作
 * </p>
 * <p>
 * Description: 对用户所拥有的相关权限进行控制和分配
 * 对用户菜单按照权限进行显示
 * 针对不同用户进行页面跳转
 * </p>
 * @author kensin
 * @version 1.0
 * @created 2008-01-10
 */
public class AcegiAction extends BaseActionSupport
{
	private static final long serialVersionUID = 1L;
	private IUserService userService;
	private String jsonString;
	public String getJsonString()
	{
		return jsonString;
	}
	public void setJsonString(String jsonString)
	{
		this.jsonString = jsonString;
	}
	public String login()
	{
		User user = userService.getUserByName(this.getGlobalUserName());
    	List<Role> roles = new ArrayList<Role>(user.getRoles());
    	int length = roles.size();
    	for(int i=0;i<length;i++)
    	{
    		if(roles.get(i).getName().equals("ROLE_SYS_PUBLIC"))
    		{
    			return "sys_public";
    		}
    	}
		return SUCCESS;
	}
	public String auths()
	{
		User user = userService.getUserByName(this.getGlobalUserName());
    	List<Role> roles = new ArrayList<Role>(user.getRoles());
    	int length = roles.size();
		String[] auths = {"0","0","0","0","0","0","0","0","0","0"};
		for(int j=0;j<length;j++)
		{
			if(roles.get(j).getName().equals("ROLE_SYS_ADMIN"))
			{
				for(int k=0;k<auths.length;k++)
				{
					auths[k]="1";
				}
				StringBuilder sb = new StringBuilder("[");
				for(int k=0;k<auths.length-1;k++)
				{
					sb.append("\'");
					sb.append(auths[k]);
					sb.append("\'");
					sb.append(",");
				}
				sb.append("\'");
				sb.append(auths[auths.length-1]);
				sb.append("\'");
				sb.append("]");
				jsonString = "{success:true,auths:"+sb.toString()+"}";
				return SUCCESS;
			}
			else
			{
				if(roles.get(j).getName().equals("ROLE_SYS_AREA"))
				{
					auths[0]="1";
				}
				else if(roles.get(j).getName().equals("ROLE_SYS_REGION"))
				{
					auths[1]="1";
				}
				else if(roles.get(j).getName().equals("ROLE_SYS_SCENERY"))
				{
					auths[2]="1";
				}
				else if(roles.get(j).getName().equals("ROLE_SYS_VISA"))
				{
					auths[3]="1";
				}
				else if(roles.get(j).getName().equals("ROLE_SYS_LINE"))
				{
					auths[4]="1";
				}
				else if(roles.get(j).getName().equals("ROLE_SYS_MANAGER"))
				{
					auths[5]="1";
				}
				else if(roles.get(j).getName().equals("ROLE_SYS_FLASH"))
				{
					auths[6]="1";
				}
				else if(roles.get(j).getName().equals("ROLE_SYS_CONFIG"))
				{
					auths[7]="1";
				}
				else if(roles.get(j).getName().equals("ROLE_SYS_PAGE"))
				{
					auths[8]="1";
				}
				
				else if(roles.get(j).getName().equals("ROLE_SYS_SHOPPER"))
				{
					auths[9]="1";
				}
				
			}
		}
		StringBuilder sb = new StringBuilder("[");
		for(int k=0;k<auths.length-1;k++)
		{
			sb.append("\'");
			sb.append(auths[k]);
			sb.append("\'");
			sb.append(",");
		}
		sb.append("\'");
		sb.append(auths[auths.length-1]);
		sb.append("\'");
		sb.append("]");
		jsonString = "{success:true,auths:"+sb.toString()+"}";
		return SUCCESS;
	}
	public IUserService getUserService()
	{
		return userService;
	}
	public void setUserService(IUserService userService)
	{
		this.userService = userService;
	}
}
