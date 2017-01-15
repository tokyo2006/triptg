package com.yeoou.tour.model;

import com.yeoou.common.model.Genericmodel;
/**
 * 行程景点图片
 * @author kensin
 * 数据库保存方式 breUrl@scId@name
 */
public class ScPic extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	//缩略图路径
	private String breUrl;
	//景点Id
	private String scId;
	//景点名称
	private String name;
	public String getBreUrl()
	{
		return breUrl;
	}
	public void setBreUrl(String breUrl)
	{
		this.breUrl = breUrl;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getScId()
	{
		return scId;
	}
	public void setScId(String scId)
	{
		this.scId = scId;
	}
}
