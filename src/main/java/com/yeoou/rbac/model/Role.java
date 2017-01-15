package com.yeoou.rbac.model;

import java.util.HashSet;
import java.util.Set;

import com.yeoou.common.model.Genericmodel;

public class Role extends Genericmodel
{
	private static final long serialVersionUID = 2409022723670104956L;
	private String roleId;
	private String name;
	private String desc;
	private Set<Resource> resources = new HashSet<Resource>();
	private Set<User> users = new HashSet<User>();
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Set<Resource> getResources()
	{
		return resources;
	}
	public void setResources(Set<Resource> resources)
	{
		this.resources = resources;
	}
	public String getRoleId()
	{
		return roleId;
	}
	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}
	public Set<User> getUsers()
	{
		return users;
	}
	public void setUsers(Set<User> users)
	{
		this.users = users;
	}
}
