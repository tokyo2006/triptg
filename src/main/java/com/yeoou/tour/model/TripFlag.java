package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.model.Genericmodel;

public class TripFlag extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String name;
	private List<TripModel> tmList = new ArrayList<TripModel>();
	public List<TripModel> getTmList()
	{
		return tmList;
	}
	public void setTmList(List<TripModel> tmList)
	{
		this.tmList = tmList;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
}
