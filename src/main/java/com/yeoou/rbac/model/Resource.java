package com.yeoou.rbac.model;

import java.util.HashSet;
import java.util.Set;

import com.yeoou.common.model.Genericmodel;

public class Resource extends Genericmodel
{
	private static final long serialVersionUID = 7118604896163725969L;
    private String rescId;
    private String name;
    private String rescType;
    private String rescString;
    private Set<Role> roles = new HashSet<Role>();
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getRescId()
	{
		return rescId;
	}
	public void setRescId(String rescId)
	{
		this.rescId = rescId;
	}
	public String getRescString()
	{
		return rescString;
	}
	public void setRescString(String rescString)
	{
		this.rescString = rescString;
	}
	public String getRescType()
	{
		return rescType;
	}
	public void setRescType(String rescType)
	{
		this.rescType = rescType;
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
