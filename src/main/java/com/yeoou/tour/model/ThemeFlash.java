package com.yeoou.tour.model;

import com.yeoou.common.model.Genericmodel;

public class ThemeFlash extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String id;
	private String url;
	private String picUrl;
	private String breUrl;
	private String title;
	private String subTitle;
	private String target;
	private String name;
	private FlashType flashType;
	private int od;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getOd()
	{
		return od;
	}
	public void setOd(int od)
	{
		this.od = od;
	}
	
	public String getBreUrl()
	{
		return breUrl;
	}
	public void setBreUrl(String breUrl)
	{
		this.breUrl = breUrl;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getPicUrl()
	{
		return picUrl;
	}
	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}
	public String getSubTitle()
	{
		return subTitle;
	}
	public void setSubTitle(String subTitle)
	{
		this.subTitle = subTitle;
	}
	public String getTarget()
	{
		return target;
	}
	public void setTarget(String target)
	{
		this.target = target;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public FlashType getFlashType()
	{
		return flashType;
	}
	public void setFlashType(FlashType flashType)
	{
		this.flashType = flashType;
	}
	
}
