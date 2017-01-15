package com.yeoou.tour.model;

import com.yeoou.common.model.Genericmodel;

public class Province extends Genericmodel
{
	private static final long serialVersionUID = 1L;
	private String provinceId;
	private String content;
	private Area area;
	private String synopsis;
	private String gloze;
	private int hit;
	private int isTop;
	private String mapUrl;
	public String getMapUrl()
	{
		return mapUrl;
	}
	public void setMapUrl(String mapUrl)
	{
		this.mapUrl = mapUrl;
	}
	public Area getArea()
	{
		return area;
	}
	public void setArea(Area area)
	{
		this.area = area;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getGloze()
	{
		return gloze;
	}
	public void setGloze(String gloze)
	{
		this.gloze = gloze;
	}
	public int getHit()
	{
		return hit;
	}
	public void setHit(int hit)
	{
		this.hit = hit;
	}
	public String getProvinceId()
	{
		return provinceId;
	}
	public void setProvinceId(String provinceId)
	{
		this.provinceId = provinceId;
	}
	public String getSynopsis()
	{
		return synopsis;
	}
	public void setSynopsis(String synopsis)
	{
		this.synopsis = synopsis;
	}
	public int getIsTop()
	{
		return isTop;
	}
	public void setIsTop(int isTop)
	{
		this.isTop = isTop;
	}


}
