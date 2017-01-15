package com.yeoou.tour.model;


import java.util.ArrayList;
import java.util.List;

import com.yeoou.rbac.model.User;

public class RegionLation extends Genericmodel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9166711061633646661L;
	private String id;
    private int haveTeam;
    private Region region;
    private User user;
    private List<DoubleRegion> doubleList = new ArrayList<DoubleRegion>();
    private List<RegionLation> childrenList = new ArrayList<RegionLation>();
	public Region getRegion()
	{
		return region;
	}
	public void setRegion(Region region)
	{
		this.region = region;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public int getHaveTeam()
	{
		return haveTeam;
	}
	public void setHaveTeam(int haveTeam)
	{
		this.haveTeam = haveTeam;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public List<RegionLation> getChildrenList()
	{
		return childrenList;
	}
	public void setChildrenList(List<RegionLation> childrenList)
	{
		this.childrenList = childrenList;
	}
	public List<DoubleRegion> getDoubleList()
	{
		return doubleList;
	}
	public void setDoubleList(List<DoubleRegion> doubleList)
	{
		this.doubleList = doubleList;
	}

	
}
