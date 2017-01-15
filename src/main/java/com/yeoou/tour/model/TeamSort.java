package com.yeoou.tour.model;

import com.yeoou.common.model.Genericmodel;

public class TeamSort extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	String name;
	String sort;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getSort()
	{
		return sort;
	}
	public void setSort(String sort)
	{
		this.sort = sort;
	}
}
