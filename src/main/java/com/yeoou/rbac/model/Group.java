package com.yeoou.rbac.model;

import java.util.HashSet;
import java.util.Set;
import com.yeoou.common.model.Genericmodel;


public class Group extends Genericmodel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3346161321587332223L;
    private String groupId;
    private String name;
    private Set<Role> roles = new HashSet<Role>();
	public String getGroupId()
	{
		return groupId;
	}
	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Set<Role> getRoles()
	{
		return roles;
	}
	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}
}
