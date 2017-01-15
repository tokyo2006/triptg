package com.yeoou.rbac.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yeoou.common.model.Genericmodel;

public class User extends Genericmodel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5442500230163230383L;
	private String userId;
	private String loginName;
	private Date loginTime;
	private Date createTime;
	private int valid;
	private String password;
	private int themeStyle;
	private Set<Group> groups= new HashSet<Group>();
	private Set<Role> roles = new HashSet<Role>();
	public Date getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public Set<Group> getGroups()
	{
		return groups;
	}
	public void setGroups(Set<Group> groups)
	{
		this.groups = groups;
	}

	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public Set<Role> getRoles()
	{
		return roles;
	}
	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public int getValid()
	{
		return valid;
	}
	public void setValid(int valid)
	{
		this.valid = valid;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public int getThemeStyle()
	{
		return themeStyle;
	}
	public void setThemeStyle(int themeStyle)
	{
		this.themeStyle = themeStyle;
	}
	
}
