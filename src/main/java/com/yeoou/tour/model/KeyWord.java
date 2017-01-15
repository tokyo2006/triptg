package com.yeoou.tour.model;

import com.yeoou.common.model.Genericmodel;

public class KeyWord extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String kpId;
	private String name;
	private String url;
	private String id;
	private String areaId;
	private int same;
	public int getSame()
	{
		return same;
	}
	public void setSame(int same)
	{
		this.same = same;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getKpId()
	{
		return kpId;
	}
	public void setKpId(String kpId)
	{
		this.kpId = kpId;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getAreaId()
	{
		return areaId;
	}
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}
}
