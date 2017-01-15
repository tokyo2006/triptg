package com.yeoou.tour.model;

import java.util.ArrayList;
import java.util.List;

import com.yeoou.common.model.Genericmodel;

public class NewsType extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String typeId;
	private String name;
	private String parent;
	private String desc;
	private NewsType pnt;
	private List<NewsType> childern = new ArrayList<NewsType>();
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public String getParent()
	{
		return parent;
	}
	public void setParent(String parent)
	{
		this.parent = parent;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getTypeId()
	{
		return typeId;
	}
	public void setTypeId(String typeId)
	{
		this.typeId = typeId;
	}
	public List<NewsType> getChildern()
	{
		return childern;
	}
	public void setChildern(List<NewsType> childern)
	{
		this.childern = childern;
	}
	public NewsType getPnt()
	{
		return pnt;
	}
	public void setPnt(NewsType pnt)
	{
		this.pnt = pnt;
	}
}
