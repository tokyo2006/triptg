package com.yeoou.tour.model;

import com.yeoou.common.model.Genericmodel;

public class FlashType extends Genericmodel
{

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String title;
	private String remark;
	public String getRemark()
	{
		return remark;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
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
